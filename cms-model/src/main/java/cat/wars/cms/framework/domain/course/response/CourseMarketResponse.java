package cat.wars.cms.framework.domain.course.response;

import cat.wars.cms.framework.domain.course.CourseMarket;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 5/20/19
 * Time: 12:48 AM
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CourseMarketResponse extends ResponseResult {

    private CourseMarket market;

    public CourseMarketResponse(CourseMarket market) {
        super(CommonCode.SUCCESS);
        this.market = market;
    }

    public CourseMarketResponse(ResultCode resultCode, CourseMarket market) {
        super(resultCode);
        this.market = market;
    }
}
