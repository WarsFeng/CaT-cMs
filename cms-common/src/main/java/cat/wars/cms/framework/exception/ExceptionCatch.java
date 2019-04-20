package cat.wars.cms.framework.exception;

import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/31/19
 * Time: 10:25 PM
 * Custom exception catch
 */

@Slf4j
@ControllerAdvice
public class ExceptionCatch {

    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();


    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException e) {
        log.error("Catch exception {}\nexception: ", e.getMessage(), e);
        return new ResponseResult(e.getResultCode());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e) {
        log.error("Catch exception {}\nexception: ", e.getMessage(), e);
        if (null == EXCEPTIONS) EXCEPTIONS = builder.build();

        final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        final ResponseResult responseResult;
        if (null == resultCode) responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        else responseResult = new ResponseResult(resultCode);
        return responseResult;
    }

    static {
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALIDPARAM);
        // put...
    }
}
