package cat.wars.cms.framework.domain.course.response;

import cat.wars.cms.framework.domain.system.SysDictionary;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/21/19
 * Time: 11:24 AM
 * Course sys dictionary response model
 */

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysDictionaryResponse extends ResponseResult {

    public SysDictionaryResponse(ResultCode resultCode, SysDictionary sysDictionary) {
        super(resultCode);
        this.sysDictionary = sysDictionary;
    }

    private SysDictionary sysDictionary;
}
