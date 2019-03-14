package cat.wars.cms.framework.domain.order.response;

import cat.wars.cms.framework.domain.order.XcOrders;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/26.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CreateOrderResult extends ResponseResult {
    private XcOrders xcOrders;

    public CreateOrderResult(ResultCode resultCode, XcOrders xcOrders) {
        super(resultCode);
        this.xcOrders = xcOrders;
    }


}
