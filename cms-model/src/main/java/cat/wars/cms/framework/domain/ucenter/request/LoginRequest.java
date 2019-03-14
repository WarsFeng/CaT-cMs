package cat.wars.cms.framework.domain.ucenter.request;

import cat.wars.cms.framework.model.request.RequestData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends RequestData {

    String username;
    String password;
    String verifycode;

}
