package cn.lfungame.exception;

import cn.lfungame.util.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: xuke
 * @Date: 2018/5/18 09:17
 * @Description:
 */
@ControllerAdvice(basePackages = {"cn.lfungame"})
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value={MethodArgumentNotValidException.class, BusinessException.class, RuntimeException.class, Exception.class})
    @ResponseBody
    public ResponseMsg exceptionHandler(Exception e, HttpServletResponse response) {
        //发生异常时清除当前会话信息
        ResponseMsg resp = new ResponseMsg();
        if(e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            BindingResult result = me.getBindingResult();
            String field = result.getFieldError().getField();
            String error = result.getFieldError().getDefaultMessage();
            resp.setCode(ErrorInfo.ARGUMENT_NOT_VALID.code);
            resp.setMessage("[" + field + "]" + error);
            //logger.error("参数较验异常["+ErrorInfo.ARGUMENT_NOT_VALID.code+"]:" + "[" + field + "]" + error, e);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return resp;
        }
        if(e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            resp.setCode(businessException.getCode());
            resp.setMessage(businessException.getMsg());
            //logger.error("自定义异常["+businessException.getCode()+"]:" + businessException.getMsg(), e);
            if(businessException.getCode() == ErrorInfo.USER_IS_NULL.code) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }else {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
            }
            return resp;
        }
        if(e instanceof RuntimeException) {
            RuntimeException runtimeException = (RuntimeException) e;
            resp.setCode(ErrorInfo.RUNTIME_EXCEPTIOON.code);
            resp.setMessage(ErrorInfo.RUNTIME_EXCEPTIOON.desc);
           // logger.error("[" + ErrorInfo.RUNTIME_EXCEPTIOON.code + "]" + ErrorInfo.RUNTIME_EXCEPTIOON.desc + ":", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return resp;
        }
        if(e instanceof Exception) {
            Exception exception = (Exception) e;
            resp.setCode(ErrorInfo.RUNTIME_EXCEPTIOON.code);
            resp.setMessage(ErrorInfo.RUNTIME_EXCEPTIOON.desc);
           // logger.error("[" + ErrorInfo.RUNTIME_EXCEPTIOON.code + "]" + ErrorInfo.RUNTIME_EXCEPTIOON.desc + ":", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return resp;
        }
        resp.setCode(ErrorInfo.UNKNOW_EXCEPTION.code);
        resp.setMessage(ErrorInfo.UNKNOW_EXCEPTION.desc);
        logger.error("[" + ErrorInfo.RUNTIME_EXCEPTIOON.code + "]" + ErrorInfo.RUNTIME_EXCEPTIOON.desc + ":", e);
        System.out.println(e.getStackTrace());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return resp;
    }
}
