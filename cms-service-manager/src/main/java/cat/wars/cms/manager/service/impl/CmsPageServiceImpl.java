package cat.wars.cms.manager.service.impl;

import cat.wars.cms.framework.domain.cms.CmsPage;
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
import cat.wars.cms.manager.service.CmsPageService;
import cat.wars.cms.manager.service.CmsTemplateService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;
import static org.springframework.util.StringUtils.isEmpty;


/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/15/19
 * Time: 6:20 PM
 * Cms page service basic implements
 */

@Service
public class CmsPageServiceImpl implements CmsPageService {

    private final CmsPageRepository repository;
    private final CmsTemplateService templateService;
    private final RestTemplate restTemplate;

    @Autowired
    public CmsPageServiceImpl(CmsPageRepository cmsPageRepository, RestTemplate restTemplate, CmsTemplateService templateService) {
        this.repository = cmsPageRepository;
        this.restTemplate = restTemplate;
        this.templateService = templateService;
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
        if (isEmpty(id)) ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);

        Optional<CmsPage> pageOptional = repository.findById(id);
        if (pageOptional.isEmpty()) ExceptionCast.cast(CmsCode.CMS_MANAGER_PAGE_NOT_EXISTS);

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

}
