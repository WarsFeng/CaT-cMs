package cat.wars.cms.manager_course.service;

import cat.wars.cms.framework.domain.course.ext.CategoryNode;
import cat.wars.cms.framework.model.response.QueryResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/20/19
 * Time: 8:38 PM
 */

public interface CourseCategoryService {

    /**
     * Like query category node list
     *
     * @param categoryName category name
     * @return cat.wars.cms.framework.model.response.QueryResponseResult
     * {@link CategoryNode}
     */
    QueryResponseResult<CategoryNode> findCategoryNodeList(String categoryName);
}
