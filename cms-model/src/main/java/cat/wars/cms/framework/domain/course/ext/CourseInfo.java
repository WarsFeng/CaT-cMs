package cat.wars.cms.framework.domain.course.ext;

import cat.wars.cms.framework.domain.course.CourseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CourseInfo extends CourseBase {

    //课程图片
    private String pic;
}
