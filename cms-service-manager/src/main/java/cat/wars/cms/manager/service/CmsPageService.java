package cat.wars.cms.manager.service;

import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.model.response.QueryResponseResult;

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
}
