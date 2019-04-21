package cat.wars.cms.manager_course.web.controller;

import cat.wars.cms.api.course.CourseControllerApi;
import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.manager_course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/19/19
 * Time: 2:12 PM
 * Course controller
 */

@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult<CourseInfo> findList(@PathVariable(name = "page") int page
            , @PathVariable(name = "size") int size, CourseListRequest params) {
        return service.findList(page, size, params);
    }
}
