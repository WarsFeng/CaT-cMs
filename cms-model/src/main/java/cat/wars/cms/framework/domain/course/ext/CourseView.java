package cat.wars.cms.framework.domain.course.ext;

import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.CourseMarket;
import cat.wars.cms.framework.domain.course.CoursePic;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/16/19
 * Time: 1:42 AM
 * Course detail view data
 */

@Data
@ToString
@NoArgsConstructor
public class CourseView {

    private CourseBase base;
    private TeachPlanNode teachPlan;
    private CoursePic pic;
    private CourseMarket market;
}