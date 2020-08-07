package com.thrcat.exceptions;

/**
 * desc:
 *
 * @author scx
 * @create 2020/04/26
 */
public class BizException extends Exception {


    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }


    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

}
