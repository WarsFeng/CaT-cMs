package cat.wars.cms.api.course;

import cat.wars.cms.framework.domain.course.TeachPlan;
import cat.wars.cms.framework.domain.course.response.TeachPlanResponse;
import cat.wars.cms.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 3:37 PM
 * Course manager controller api
 */

@Api("Cms teach course management interface")
public interface CourseControllerApi {

    @ApiOperation("Query teach plan")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    TeachPlanResponse findTeachPlanList(String courseId);

    @ApiOperation("Add teach plan")
    ResponseResult addTeachPlan(TeachPlan teachPlan);
}
