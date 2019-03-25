package cat.wars.cms.manager.service;

import cat.wars.cms.framework.model.response.QueryResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/24/19
 * Time: 10:41 PM
 * Cms site service
 */

public interface CmsSiteService {

    /**
     * Find site list of id,name
     * limit 10 and sort by createTime
     *
     * @param query site name
     * @return cat.wars.cms.framework.model.response.QueryResponseResult
     */
    QueryResponseResult findSubList(String query);
}
