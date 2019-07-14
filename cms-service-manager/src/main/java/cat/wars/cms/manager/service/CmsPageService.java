package cat.wars.cms.manager.service;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.ResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/15/19
 * Time: 5:38 PM
 * Cms page service
 */

public interface CmsPageService {

    /**
     * Paging query page
     *
     * @param page    page num
     * @param size    page size
     * @param request param, {@link CmsQueryPageRequest}
     * @return cat.wars.cms.framework.model.response.QueryResponseResult
     */
    QueryResponseResult<CmsPage> findList(int page, int size, CmsQueryPageRequest request);

    /**
     * Add page
     *
     * @param cmsPage {@link CmsPage}
     * @return cat.wars.cms.framework.domain.cms.response.CmsPageResult
     */
    CmsPageResult add(CmsPage cmsPage);

    /**
     * Query page by id, if return, always not null
     *
     * @param id page id
     * @return cat.wars.cms.framework.domain.cms.response.CmsPageResult
     */
    CmsPageResult findById(String id);

    /**
     * Get page by id, if return, always not null
     *
     * @param id page id
     * @return cat.wars.cms.framework.domain.cms.CmsPage
     */
    CmsPage getById(String id);

    /**
     * Edit page by id
     *
     * @param id   page id
     * @param page {@link CmsPage}
     * @return cat.wars.cms.framework.domain.cms.response.CmsPageResult
     */
    CmsPageResult edit(String id, CmsPage page);

    /**
     * Delete page by id
     *
     * @param id page id
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult delete(String id);

    /**
     * Page static by id
     *
     * @param id page id
     * @return static html string
     */
    String getPageHtml(String id);

    /**
     * Release page by id
     * Page static and push message to mq, mq format: {pageId : ""}
     *
     * @param id page id
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult release(String id);

    /**
     * Save page, add or update
     *
     * @param page {@link CmsPage}
     * @return cat.wars.cms.framework.domain.cms.response.CmsPageResult
     */
    CmsPageResult save(CmsPage page);

    /**
     * Quick release page
     *
     * @param page {@link CmsPage}
     * @return cat.wars.cms.framework.domain.cms.response.CmsPostPageResult
     */
    CmsPostPageResult releaseQuick(CmsPage page);
}
