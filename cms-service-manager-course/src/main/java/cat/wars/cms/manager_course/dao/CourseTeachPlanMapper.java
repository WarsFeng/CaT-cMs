package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.ext.TeachPlanNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 6:30 PM
 * Teach plan mapper
 */

@Mapper
public interface CourseTeachPlanMapper {

    TeachPlanNode findNodeList(String courseId);
}
