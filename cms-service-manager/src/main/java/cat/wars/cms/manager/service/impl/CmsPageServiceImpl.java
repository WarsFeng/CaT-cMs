package cat.wars.cms.manager.service.impl;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.domain.cms.response.CmsCode;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.manager.dao.CmsPageRepository;
import cat.wars.cms.manager.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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

    @Autowired
    public CmsPageServiceImpl(CmsPageRepository cmsPageRepository) {
        this.repository = cmsPageRepository;
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
            return new CmsPageResult(CmsCode.CMS_MANAGER_REQUEST_INVALID, null);

        // Check if the page exists
        Optional<CmsPage> pageOptional = repository.findBySiteIdAndPageWebPathAndPageName(page.getSiteId(), page.getPageWebPath(), page.getPageName());
        if (pageOptional.isPresent()) return new CmsPageResult(CmsCode.CMS_ADDPAGE_EXISTSNAME, null);
        // Avoid injection
        page.setPageId(null);
        return new CmsPageResult(CommonCode.SUCCESS, repository.save(page));
    }
}
