package cat.wars.cms.api.course;

import cat.wars.cms.framework.domain.course.response.CourseCoverResponse;
import cat.wars.cms.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/5/19
 * Time: 12:12 AM
 * Course image api
 */

@Api("Course image api")
public interface CourseImageControllerApi {

    @ApiOperation("Save course cover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "fs image id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "courseId", value = "course id", required = true, paramType = "path", dataType = "string")
    })
    ResponseResult saveCourseCover(String courseId, String fileId);

    @ApiOperation("Find course cover image by cid")
    @ApiImplicitParam(name = "courseId", value = "course id", required = true, paramType = "path", dataType = "string")
    CourseCoverResponse findCoverByCourseId(String courseId);

    @ApiOperation("Delete course cover image by cid")
    @ApiImplicitParam(name = "courseId", value = "course id", required = true, paramType = "path", dataType = "string")
    ResponseResult deleteCoverByCourseId(String courseId);
}
