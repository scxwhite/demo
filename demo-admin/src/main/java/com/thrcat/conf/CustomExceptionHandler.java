package com.thrcat.conf;


import com.thrcat.Constants;
import com.thrcat.exceptions.BizException;
import com.thrcat.exceptions.NoPermissionException;
import com.thrcat.exceptions.UnLoginException;
import com.thrcat.logs.ErrorLog;
import com.thrcat.model.JsonResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


/**
 * 统一异常拦截
 *
 * @author scx
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NoPermissionException.class)
    public JsonResponse noPermissionException(HttpServletRequest request, Exception ex) {
        ErrorLog.error("权限异常", ex);
        return new JsonResponse(Constants.VALID_CODE, null, ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResponse constraintViolationException(HttpServletRequest request, Exception ex) {
        ErrorLog.error("参数异常", ex);
        return new JsonResponse(Constants.VALID_CODE, null, ex.getMessage());
    }

    @ExceptionHandler(UnLoginException.class)
    public JsonResponse unLoginException(HttpServletRequest request, Exception ex) {
        ErrorLog.error("未登录异常", ex);
        return new JsonResponse(Constants.UN_LOGIN_CODE, null, "未登录");
    }

    @ExceptionHandler(BizException.class)
    public JsonResponse bizException(HttpServletRequest request, Exception ex) {
        ErrorLog.error("业务处理异常", ex);
        String errorMsg = ex.getMessage();
        return new JsonResponse(Constants.NO_PERMISSION_CODE, null, "业务处理异常:" + errorMsg);
    }

    @ExceptionHandler(Exception.class)
    public JsonResponse exception(HttpServletRequest request, Exception ex) {
        ErrorLog.error("系统异常", ex);
        return new JsonResponse(Constants.ERROR_CODE, null, "系统异常");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResponse httpRequestMethodNotSupportedException(HttpServletRequest request, Exception ex) {
        ErrorLog.error("不支持的请求类型", ex);
        return new JsonResponse(Constants.ERROR_CODE, null, "不支持的请求类型");
    }
}
