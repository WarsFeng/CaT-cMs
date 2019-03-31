package cat.wars.cms.manager.service;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
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
    QueryResponseResult findList(int page, int size, CmsQueryPageRequest request);

    /**
     * Add page
     *
     * @param cmsPage {@link CmsPage}
     * @return cat.wars.cms.framework.domain.cms.response.CmsPageResult
     */
    CmsPageResult add(CmsPage cmsPage);

    /**
     * Query page by id
     *
     * @param id page id
     * @return cat.wars.cms.framework.domain.cms.response.CmsPageResult
     */
    CmsPageResult findById(String id);

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
}
