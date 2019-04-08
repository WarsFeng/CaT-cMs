package cat.wars.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.ext.TeachPlanNode;
import cat.wars.cms.framework.domain.course.response.CourseCode;
import cat.wars.cms.framework.domain.course.response.TeachPlanResponse;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.manager_course.dao.TeachPlanMapper;
import cat.wars.manager_course.service.TeachPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 9:31 PM
 * Course teach plan service basic implementation
 */

@Service
public class TeachPlanServiceImpl implements TeachPlanService {

    private final TeachPlanMapper mapper;

    public TeachPlanServiceImpl(TeachPlanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TeachPlanResponse findTeachPlanList(String courseId) {
        if (StringUtils.isEmpty(courseId)) ExceptionCast.cast(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);

        TeachPlanNode teachPlanNode = mapper.findNodeList(courseId);
        return new TeachPlanResponse(CommonCode.SUCCESS, teachPlanNode);
    }
}
