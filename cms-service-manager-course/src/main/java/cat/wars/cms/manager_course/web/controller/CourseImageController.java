package cat.wars.cms.manager_course.web.controller;

import cat.wars.cms.api.course.CourseImageControllerApi;
import cat.wars.cms.framework.domain.course.response.CourseCoverResponse;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.service.CourseImageService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/5/19
 * Time: 2:34 AM
 */

@RestController
@RequestMapping("/course/image")
public class CourseImageController implements CourseImageControllerApi {

    private final CourseImageService service;

    public CourseImageController(CourseImageService service) {
        this.service = service;
    }

    @Override
    @PutMapping("/cover/save/{courseId}")
    public ResponseResult saveCourseCover(
            @PathVariable(name = "courseId") String courseId
            , @RequestParam String fileId
    ) {
        return service.saveCourseCover(courseId, fileId);
    }

    @Override
    @GetMapping("/cover/{courseId}")
    public CourseCoverResponse findCoverByCourseId(@PathVariable(name = "courseId") String courseId) {
        return service.findCoverByCourseId(courseId);
    }

    @Override
    @DeleteMapping("/cover/{courseId}")
    public ResponseResult deleteCoverByCourseId(@PathVariable(name = "courseId") String courseId) {
        return service.deleteCoverByCourseId(courseId);
    }
}
