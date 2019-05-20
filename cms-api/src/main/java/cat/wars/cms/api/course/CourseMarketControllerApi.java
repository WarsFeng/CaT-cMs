package cat.wars.cms.api.course;

import cat.wars.cms.framework.domain.course.CourseMarket;
import cat.wars.cms.framework.domain.course.response.CourseMarketResponse;
import cat.wars.cms.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 5/19/19
 * Time: 11:56 PM
 * Course market api
 */

@Api("Course market api")
public interface CourseMarketControllerApi {

    @ApiOperation("Get market by id")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    CourseMarketResponse getById(String id);

    @ApiOperation("Save market")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    ResponseResult save(String id, CourseMarket market);
}
