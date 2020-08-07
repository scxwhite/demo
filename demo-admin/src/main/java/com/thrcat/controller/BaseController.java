package com.thrcat.controller;

import com.thrcat.Constants;
import com.thrcat.model.JsonResponse;
import com.thrcat.utils.JwtUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * desc:
 *
 * @author scx
 * @create 2020/03/20
 */
public abstract class BaseController {

    private static final ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<>();


    public static void remove() {
        requestThread.remove();
    }

    public static void set(HttpServletRequest request) {
        requestThread.set(request);
    }


    @ModelAttribute
    protected void setRequest(HttpServletRequest request) {
        requestThread.set(request);
    }

    protected JsonResponse success(Object data) {
        return new JsonResponse(Constants.SUCCESS_CODE, data);
    }

    protected JsonResponse success(Object data, String message) {
        return new JsonResponse(Constants.SUCCESS_CODE, data, message);
    }

    protected JsonResponse success() {
        return new JsonResponse(Constants.SUCCESS_CODE, "处理成功");
    }

    protected JsonResponse failed() {
        return new JsonResponse(Constants.ERROR_CODE, null, "处理失败");
    }

    protected JsonResponse failed(Object data) {
        return new JsonResponse(Constants.ERROR_CODE, data);
    }

    protected JsonResponse failed(String msg) {
        return new JsonResponse(Constants.ERROR_CODE, null, msg);
    }

    protected JsonResponse failed(Object data, String msg) {
        return new JsonResponse(Constants.ERROR_CODE, data, msg);
    }


    protected String getOwner() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_USERNAME);
    }

    protected String getSsoName() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_SSO_NAME);
    }

    protected String getOwnerId() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_USER_ID);
    }

    protected String getSsoId() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_SSO_ID);
    }

    public boolean isAdmin() {
        //TODO 这里可以通过getOwner/getSsoName/getOwnerId/getSsoId 来判断
        return false;
    }
}
