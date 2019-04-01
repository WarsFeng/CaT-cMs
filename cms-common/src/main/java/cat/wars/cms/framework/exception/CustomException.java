package cat.wars.cms.framework.exception;

import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/31/19
 * Time: 10:17 PM
 * Custom runtime exception
 */

@Getter
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super("Error code: " + resultCode.code() + ", message: " + resultCode.message());
        this.resultCode = resultCode;
    }
}
