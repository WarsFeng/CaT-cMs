package cat.wars.cms.manager_course.web.controller;

import cat.wars.cms.api.course.CourseTeachPlanControllerApi;
import cat.wars.cms.framework.domain.course.TeachPlan;
import cat.wars.cms.framework.domain.course.response.TeachPlanResponse;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.service.CourseTeachPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 9:38 PM
 */

@Slf4j
@RestController
@RequestMapping("/course/teachplan")
public class CourseTeachPlanController implements CourseTeachPlanControllerApi {

    private final CourseTeachPlanService service;

    public CourseTeachPlanController(CourseTeachPlanService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/list/{courseId}")
    public TeachPlanResponse findList(@PathVariable(name = "courseId") String courseId) {
        log.info("Query teachPlan list by courseId, courseId({})", courseId);
        return service.findTeachPlanList(courseId);
    }

    @Override
    @PostMapping("/add")
    public ResponseResult add(@RequestBody TeachPlan teachPlan) {
        log.info("Add teachPlan, teachPlan(\n\t{}\n)", teachPlan);
        return service.add(teachPlan);
    }
}
