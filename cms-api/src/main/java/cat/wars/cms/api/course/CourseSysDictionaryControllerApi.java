package cat.wars.cms.api.course;

import cat.wars.cms.framework.domain.course.response.SysDictionaryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/21/19
 * Time: 11:22 AM
 * Course sys dictionary api
 */

@Api("Course sys dictionary api")
public interface CourseSysDictionaryControllerApi {

    @ApiOperation("Get sys dictionary by type")
    @ApiImplicitParam(name = "type", value = "dictionary type", required = true, paramType = "path", dataType = "string")
    SysDictionaryResponse getByType(String type);
}
