package cat.wars.cms.framework.exception;

import cat.wars.cms.framework.model.response.ResultCode;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/31/19
 * Time: 10:24 PM
 * Custom exception cast
 */

public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}