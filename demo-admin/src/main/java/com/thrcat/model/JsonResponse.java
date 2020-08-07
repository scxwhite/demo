package com.thrcat.model;

import com.thrcat.Constants;

/**
 * desc:
 *
 * @author scx
 * @create 2020/03/19
 */
public class JsonResponse {

    private Integer code;
    private Object data;
    private Boolean success;
    private String message = "查询成功";


    public JsonResponse(Integer code, Object data) {
        this.data = data;
        this.code = code;
        this.success = code.equals(Constants.SUCCESS_CODE);
    }

    public JsonResponse(Integer code, Object data, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.success = code.equals(Constants.SUCCESS_CODE);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
