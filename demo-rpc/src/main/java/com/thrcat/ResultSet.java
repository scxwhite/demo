package com.thrcat;

import java.io.Serializable;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/05
 */

public class ResultSet<T> implements Serializable {

    /**
     * 结果
     */
    private T data;

    /**
     * 此次通信是否有异常，注意:不是最终结果
     */
    private boolean success;

    /**
     * 提示信息
     */
    private String msg;


    public ResultSet(T data, boolean success, String msg) {
        this.data = data;
        this.success = success;
        this.msg = msg;
    }


    public ResultSet(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static <T> ResultSet<T> ofSuccess(T data) {
        return new ResultSet<>(data, true, "请求成功");
    }


    public static <T> ResultSet<T> ofFailed() {
        return new ResultSet<>(false, "请求失败");
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

