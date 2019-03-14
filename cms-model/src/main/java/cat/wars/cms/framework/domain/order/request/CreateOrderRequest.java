package cat.wars.cms.framework.domain.order.request;

import cat.wars.cms.framework.model.request.RequestData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/26.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CreateOrderRequest extends RequestData {

    String courseId;

}
