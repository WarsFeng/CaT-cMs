package cat.wars.cms.framework.domain.cms.response;

import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by mrt on 2018/3/31.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GenerateHtmlResult extends ResponseResult {
    String html;

    public GenerateHtmlResult(ResultCode resultCode, String html) {
        super(resultCode);
        this.html = html;
    }
}
