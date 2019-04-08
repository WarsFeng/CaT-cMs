package cat.wars.manager_course.service;

import cat.wars.cms.framework.domain.course.response.TeachPlanResponse;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 9:26 PM
 * Course teach plan service
 */

public interface TeachPlanService {

    /**
     * Query course teach plan
     *
     * @param courseId course id
     * @return cat.wars.cms.framework.domain.course.response.TeachPlanResponse
     */
    TeachPlanResponse findTeachPlanList(String courseId);
}
