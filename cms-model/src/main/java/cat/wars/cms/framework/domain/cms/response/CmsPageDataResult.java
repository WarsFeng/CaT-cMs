package cat.wars.cms.framework.domain.cms.response;

import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/30/19
 * Time: 5:29 PM
 * Cms page data result
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CmsPageDataResult extends ResponseResult {

    private Map<String, Object> data;

    public CmsPageDataResult(ResultCode resultCode, Map<String, Object> data) {
        super(resultCode);
        this.data = data;
    }
}
