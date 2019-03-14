package cat.wars.cms.framework.domain.order.response;

import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/27.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class PayQrcodeResult extends ResponseResult {
    public PayQrcodeResult(ResultCode resultCode) {
        super(resultCode);
    }

    private String codeUrl;
    private Float money;
    private String orderNumber;

}
