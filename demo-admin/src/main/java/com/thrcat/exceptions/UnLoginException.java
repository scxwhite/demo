package com.thrcat.exceptions;


/**
 * 未登陆异常
 *
 * @author scx
 */
public class UnLoginException extends RuntimeException {

    public UnLoginException() {
        super();
    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(Throwable e) {
        super(e);
    }

    public UnLoginException(String msg, Throwable e) {
        super(msg, e);
    }
}
