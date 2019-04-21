package cat.wars.cms.manager_course.service;

import cat.wars.cms.framework.domain.course.TeachPlan;
import cat.wars.cms.framework.domain.course.response.TeachPlanResponse;
import cat.wars.cms.framework.model.response.ResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 9:26 PM
 * Course teach plan service
 */

public interface CourseTeachPlanService {

    /**
     * Query course teach plan
     *
     * @param courseId course id
     * @return cat.wars.cms.framework.domain.course.response.TeachPlanResponse
     */
    TeachPlanResponse findTeachPlanList(String courseId);

    /**
     * Get teach plan root node id, if not exists, create it and return
     *
     * @param courseId {@link cat.wars.cms.framework.domain.course.CourseBase} id
     * @return cat.wars.cms.framework.domain.course.TeachPlan
     */
    String getTeachPlanRootId(String courseId);

    /**
     * Add teach plan
     *
     * @param teachPlan {@link TeachPlan}
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult add(TeachPlan teachPlan);
}
