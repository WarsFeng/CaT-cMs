package cat.wars.cms.api.course;

import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import cat.wars.cms.framework.domain.cms.response.CoursePreviewResult;
import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.domain.course.response.CourseResponse;
import cat.wars.cms.framework.domain.course.response.CourseViewResult;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 3:37 PM
 * Course manager controller api
 */

@Api("Course manager course api")
public interface CourseControllerApi {

    @ApiOperation("Paging query course")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page num", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "size", value = "page size num", required = true, paramType = "path", dataType = "string")})
    QueryResponseResult findList(int page, int size, CourseListRequest params);

    @ApiOperation("Add course")
    ResponseResult add(CourseBase course);

    @ApiOperation("Query course by id")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    CourseResponse getById(String id);

    @ApiOperation("Update course")
    ResponseResult edit(String id, CourseBase course);

    @ApiOperation("Query course view data")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    CourseViewResult view(String id);

    @ApiOperation("Course release preview")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    CoursePreviewResult preview(String id);

    @ApiOperation("Course formal release")
    @ApiImplicitParam(name = "id", value = "course id", required = true, paramType = "path", dataType = "string")
    CmsPostPageResult release(@PathVariable("id") String id);
}
