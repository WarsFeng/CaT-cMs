package cat.wars.cms.manager.service.impl;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.CmsSite;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.domain.cms.response.CmsCode;
import cat.wars.cms.framework.domain.cms.response.CmsConfigResult;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager.dao.CmsPageRepository;
import cat.wars.cms.manager.dao.CmsSiteRepository;
import cat.wars.cms.manager.service.CmsPageService;
import cat.wars.cms.manager.service.CmsTemplateService;
import com.alibaba.fastjson.JSON;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;


/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/15/19
 * Time: 6:20 PM
 * Cms page service basic implements
 */

@Service
public class CmsPageServiceImpl implements CmsPageService {

    @Value("${spring.rabbitmq.params.exchange.page-release}")
    private String EXCHANGE_ROUTING_CMS_PAGE_RELEASE;

    private final CmsPageRepository repository;
    private final CmsTemplateService templateService;
    private final CmsSiteRepository siteRepository;
    private final RestTemplate restTemplate;
    private final GridFsTemplate gridFsTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CmsPageServiceImpl(CmsPageRepository cmsPageRepository, RestTemplate restTemplate, CmsTemplateService templateService, CmsSiteRepository siteRepository, GridFsTemplate gridFsTemplate, RabbitTemplate rabbitTemplate) {
        this.repository = cmsPageRepository;
        this.restTemplate = restTemplate;
        this.templateService = templateService;
        this.siteRepository = siteRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public QueryResponseResult findList(int page, int size, CmsQueryPageRequest request) {
        // Page, size process
        if (1 > page) page = 1;
        if (1 > size) size = 15;
        // abstract page to real page
        --page;

        // Define PageAble and sort
        PageRequest pageAble = PageRequest.of(page, size
                , Sort.by(Sort.Direction.DESC, "pageCreateTime"));

        Page<CmsPage> pages;
        if (request.isNotValidAddress()) {
            pages = repository.findAll(pageAble);
        } else { // Find by example
            Example<CmsPage> example;
            CmsPage probe = new CmsPage();
            if (isNotEmpty(request.getSiteId())) probe.setSiteId(request.getSiteId());
            if (isNotEmpty(request.getTemplateId())) probe.setTemplateId(request.getTemplateId());
            if (isNotEmpty(request.getPageId())) probe.setPageId(request.getPageId());
            if (isNotEmpty(request.getPageName())) probe.setPageName(request.getPageName());

            if (isEmpty(request.getPageAlias())) {
                example = Example.of(probe);
            } else { // Fuzzy query by alias(contains)
                ExampleMatcher matching = ExampleMatcher.matching()
                        .withMatcher("pageAlias", matcher -> matcher.contains());
                probe.setPageAlias(request.getPageAlias());

                example = Example.of(probe, matching);
            }
            pages = repository.findAll(example, pageAble);
        }
        // Result
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(pages.getContent());
        queryResult.setTotal(pages.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public CmsPageResult add(CmsPage page) {
        if (isEmpty(page.getSiteId()) || isEmpty(page.getPageWebPath()) || isEmpty(page.getPageName()))
            ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);

        // Check if the page exists
        Optional<CmsPage> pageOptional = repository.findBySiteIdAndPageWebPathAndPageName(page.getSiteId(), page.getPageWebPath(), page.getPageName());
        if (pageOptional.isPresent()) ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        // Avoid injection
        page.setPageId(null);
        return new CmsPageResult(CommonCode.SUCCESS, repository.save(page));
    }

    @Override
    public CmsPageResult findById(String id) {
        return new CmsPageResult(CommonCode.SUCCESS, getById(id));
    }

    @Override
    public CmsPage getById(String id) {
        // Filter request data
        Optional<CmsPage> pageOptional = null;
        if (isEmpty(id) || (pageOptional = repository.findById(id)).isEmpty())
            ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);

        return pageOptional.get();
    }

    @Override
    public CmsPageResult edit(String id, CmsPage page) {
        CmsPage dPage = getById(id);

        // Site id
        dPage.setSiteId(page.getSiteId());
        // Template id
        dPage.setTemplateId(page.getTemplateId());
        // Page name
        dPage.setPageName(page.getPageName());
        // Page alias
        dPage.setPageAlias(page.getPageAlias());
        // Page web  path
        dPage.setPageWebPath(page.getPageWebPath());
        // Page physical path
        dPage.setPagePhysicalPath(page.getPagePhysicalPath());
        // Page type(dynamic-1, static-0)
        dPage.setPageType(page.getPageType());
        // Page data url
        dPage.setDataUrl(page.getDataUrl());
        // Page create time
        dPage.setPageCreateTime(page.getPageCreateTime());

        // Save
        CmsPage save = repository.save(dPage);
        return new CmsPageResult(CommonCode.SUCCESS, save);
    }

    @Override
    public ResponseResult delete(String id) {
        getById(id); // If exists
        repository.deleteById(id);
        return ResponseResult.SUCCESS();
    }

    public String getPageHtml(String id) {
        // Get page
        CmsPage page = getById(id);

        // Get template data
        String templateStr = templateService.getTemplateStr(page.getTemplateId());

        // Get model data by dataUrl
        if (isEmpty(page.getDataUrl())) ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        ResponseEntity<CmsConfigResult> responseEntity = restTemplate.getForEntity(page.getDataUrl(), CmsConfigResult.class);
        CmsConfigResult cmsConfigResult = responseEntity.getBody();
        if (null == cmsConfigResult || !cmsConfigResult.isSuccess())
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);

        // Merge to html
        Configuration configuration = new Configuration(Configuration.getVersion());
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate("template", templateStr);
        configuration.setTemplateLoader(templateLoader);
        try {
            Template template = configuration.getTemplate("template");
            // Result
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, cmsConfigResult.getConfig());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseResult release(String id) {
        CmsPage page = getById(id); // Get page
        if (isEmpty(page.getPagePhysicalPath())) ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_PAGE_PHYSICALPATH_ISNULL);

        if (isNotEmpty(page.getHtmlFileId())) // Delete the original
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(page.getHtmlFileId())));
        // Upload
        String pageHtmlStr = getPageHtml(id);
        InputStream pageHtmlStream = IOUtils.toInputStream(pageHtmlStr, Charset.forName("UTF-8"));
        ObjectId objectId = gridFsTemplate.store(pageHtmlStream, page.getPageName());
        /// Update page
        page.setHtmlFileId(objectId.toString());
        repository.save(page);

        Optional<CmsSite> siteOptional = null;
        if (isEmpty(page.getSiteId()) || (siteOptional = siteRepository.findById(page.getSiteId())).isEmpty())
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_PAGE_SITE_ISNULL);
        if (isEmpty(siteOptional.get().getSitePhysicalPath()))
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_PAGE_SITEPHYSICALPATH_ISNULL);

        // Push message
        rabbitTemplate.convertAndSend(EXCHANGE_ROUTING_CMS_PAGE_RELEASE, page.getSiteId(), JSON.toJSON(Map.of("pageId", page.getPageId())));
        return ResponseResult.SUCCESS();
    }

}
