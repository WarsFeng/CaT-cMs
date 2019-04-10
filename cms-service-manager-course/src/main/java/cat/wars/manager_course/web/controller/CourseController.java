package cat.wars.manager_course.web.controller;

import cat.wars.cms.api.course.CourseControllerApi;
import cat.wars.cms.framework.domain.course.TeachPlan;
import cat.wars.cms.framework.domain.course.response.TeachPlanResponse;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.manager_course.service.TeachPlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 9:38 PM
 */

@RestController
@RequestMapping("/course/teachplan")
public class CourseController implements CourseControllerApi {

    private final TeachPlanService service;

    public CourseController(TeachPlanService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/list/{courseId}")
    public TeachPlanResponse findTeachPlanList(@PathVariable(name = "courseId") String courseId) {
        return service.findTeachPlanList(courseId);
    }

    @Override
    @GetMapping("/add")
    public ResponseResult addTeachPlan(TeachPlan teachPlan) {
        return null;
    }
}
