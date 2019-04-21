package cat.wars.cms.api.course;

import cat.wars.cms.framework.domain.course.ext.CategoryNode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/20/19
 * Time: 8:28 PM
 * Course category api
 */

@Api("Course category api")
public interface CourseCategoryControllerApi {

    @ApiOperation("Query category node list")
    @ApiImplicitParam(name = "categoryName", value = "category name", required = false, paramType = "query", dataType = "string")
    QueryResponseResult<CategoryNode> findCategoryNodeList(String categoryName);
}
