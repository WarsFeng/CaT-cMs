package cat.wars.cms.framework.domain.course.response;

import cat.wars.cms.framework.domain.course.ext.CourseView;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/30/19
 * Time: 5:34 PM
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CourseViewResult extends ResponseResult {

    private CourseView data; // name is data, not edit it, data specification

    public CourseViewResult(ResultCode resultCode, CourseView data) {
        super(resultCode);
        this.data = data;
    }
}
