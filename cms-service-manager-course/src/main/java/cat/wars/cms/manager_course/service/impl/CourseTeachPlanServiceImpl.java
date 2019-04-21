package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.TeachPlan;
import cat.wars.cms.framework.domain.course.ext.TeachPlanNode;
import cat.wars.cms.framework.domain.course.response.CourseCode;
import cat.wars.cms.framework.domain.course.response.TeachPlanResponse;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.dao.CourseRepository;
import cat.wars.cms.manager_course.dao.CourseTeachPlanMapper;
import cat.wars.cms.manager_course.dao.CourseTeachPlanRepository;
import cat.wars.cms.manager_course.service.CourseTeachPlanService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 9:31 PM
 * Course teach plan service basic implementation
 */

@Service
public class CourseTeachPlanServiceImpl implements CourseTeachPlanService {

    private final CourseTeachPlanMapper mapper;
    private final CourseTeachPlanRepository repository;
    private final CourseRepository courseRepository;

    public CourseTeachPlanServiceImpl(CourseTeachPlanMapper mapper, CourseTeachPlanRepository repository, CourseRepository courseRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @Override
    public TeachPlanResponse findTeachPlanList(String courseId) {
        if (isEmpty(courseId)) ExceptionCast.cast(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);

        TeachPlanNode teachPlanNode = mapper.findNodeList(courseId);

        return new TeachPlanResponse(CommonCode.SUCCESS, teachPlanNode);
    }

    @Override
    public String getTeachPlanRootId(String courseId) {
        Optional<CourseBase> courseOptional;
        if ((courseOptional = courseRepository.findById(courseId)).isEmpty())
            ExceptionCast.cast(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);

        Optional<TeachPlan> teachPlanOptional = repository.findByParentidAndCourseid("0", courseId);

        TeachPlan teachPlan;
        if (teachPlanOptional.isEmpty()) { // Create root node
            teachPlan = new TeachPlan();
            teachPlan.setPname(courseOptional.get().getId());
            teachPlan.setParentid("0");
            teachPlan.setGrade("1");
            teachPlan.setStatus("0");
            teachPlan.setCourseid(courseId);
            teachPlan = repository.save(teachPlan);
        } else teachPlan = teachPlanOptional.get();

        return teachPlan.getId();
    }

    @Override
    public ResponseResult add(TeachPlan teachPlan) {
        // Verify data
        if (isEmpty(teachPlan.getCourseid()) || isEmpty(teachPlan.getPname()) || isEmpty(teachPlan.getStatus()))
            ExceptionCast.cast(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        // TODO Verify course owner

        // Parent and grade
        if (isEmpty(teachPlan.getParentid())) { // Secondary node
            teachPlan.setParentid(this.getTeachPlanRootId(teachPlan.getCourseid())); // Set root node
            teachPlan.setGrade("2");
        } else {
            Optional<String> courseIdOptional = repository.findCourseidById(teachPlan.getParentid());
            if (courseIdOptional.isEmpty() || !teachPlan.getCourseid().equals(courseIdOptional.get())) // Verify parent
                ExceptionCast.cast(CourseCode.COURSE_TEACH_PLAN_PARENT_NOT_EXISTS);
            teachPlan.setGrade("3");
        }
        // Order
        Optional<Integer> orderbyOptional = repository.findOrderbyByParentId(teachPlan.getParentid());
        if (orderbyOptional.isEmpty()) teachPlan.setOrderby(1);
        else teachPlan.setOrderby(orderbyOptional.get());

        // Save
        repository.save(teachPlan);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
