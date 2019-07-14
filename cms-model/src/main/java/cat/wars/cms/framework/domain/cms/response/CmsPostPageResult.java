package cat.wars.cms.framework.domain.cms.response;

import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 7/8/19
 * Time: 2:49 PM
 * Cms publish page result
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CmsPostPageResult extends ResponseResult {

    private String pageUrl;

    public CmsPostPageResult(ResultCode resultCode, String pageUrl) {
        super(resultCode);
        this.pageUrl = pageUrl;
    }
}
