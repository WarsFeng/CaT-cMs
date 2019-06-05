package cat.wars.cms.framework.domain.course.response;

import cat.wars.cms.framework.domain.course.CoursePic;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/5/19
 * Time: 12:13 PM
 * Course cover image response
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CourseCoverResponse extends ResponseResult {

    private CoursePic cover;

    public CourseCoverResponse(CoursePic cover) {
        super(CommonCode.SUCCESS);
        this.cover = cover;
    }

    public CourseCoverResponse(ResultCode resultCode, CoursePic cover) {
        super(resultCode);
        this.cover = cover;
    }
}
