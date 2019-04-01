package cat.wars.cms.framework.domain.cms.response;

import cat.wars.cms.framework.domain.cms.CmsConfig;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 8:41 AM
 * Cms config response result
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CmsConfigResult extends ResponseResult {

    private CmsConfig config;

    public CmsConfigResult() {
    }

    public CmsConfigResult(ResultCode resultCode, CmsConfig config) {
        super(resultCode);
        this.config = config;
    }
}
