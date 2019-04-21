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
 * Date: 4/18/19
 * Time: 9:20 PM
 * Course teach plan controller api
 */

@Api("Course manager teach plan api")
public interface CourseTeachPlanControllerApi {

    @ApiOperation("Query teach plan")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    TeachPlanResponse findList(String courseId);

    @ApiOperation("Add teach plan")
    ResponseResult add(TeachPlan teachPlan);
}
