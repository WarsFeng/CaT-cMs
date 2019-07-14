package cat.wars.cms.manager_course.web.controller;

import cat.wars.cms.api.course.CourseControllerApi;
import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import cat.wars.cms.framework.domain.cms.response.CoursePreviewResult;
import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.domain.course.response.CourseResponse;
import cat.wars.cms.framework.domain.course.response.CourseViewResult;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        log.info("Query course list, page({}), size({}), params(\n\t{}\n)", page, size, params);
        return service.findList(page, size, params);
    }

    @Override
    @PostMapping("/add")
    public ResponseResult add(@RequestBody CourseBase course) {
        log.info("Add course, course(\n\t{}\n)", course);
        return service.add(course);
    }

    @Override
    @GetMapping("/get/{id}")
    public CourseResponse getById(@PathVariable(name = "id") String id) {
        log.info("Query course by id, id({})", id);
        return service.findById(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseResult edit(@PathVariable(name = "id") String id, @RequestBody CourseBase course) {
        log.info("Update course, id({}), course(\n\t{}\n)", id, course);
        return service.edit(course);
    }

    @Override
    @GetMapping("/view/{id}")
    public CourseViewResult view(@PathVariable("id") String id) {
        return service.view(id);
    }

    @Override
    @PostMapping("/preview/{id}")
    public CoursePreviewResult preview(@PathVariable("id") String id) {
        return service.preview(id);
    }

    @Override
    @PostMapping("/release/{id}")
    public CmsPostPageResult release(@PathVariable("id") String id) {
        return service.release(id);
    }
}
