package cat.wars.cms.framework.domain.course.response;

import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/21/19
 * Time: 11:49 PM
 * Get course response
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CourseResponse extends ResponseResult {

    public CourseResponse(ResultCode resultCode, CourseBase course) {
        super(resultCode);
        this.course = course;
    }

    private CourseBase course;
}
