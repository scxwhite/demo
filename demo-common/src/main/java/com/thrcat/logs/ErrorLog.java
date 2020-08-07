package com.thrcat.logs;


import org.apache.log4j.Logger;

/**
 * @author scx
 */
public class ErrorLog {

    static Logger log = Logger.getLogger(ErrorLog.class);

    public static void error(String msg) {
        log.error(msg);
    }

    public static void error(String msg, Exception e) {
        log.error(msg, e);
    }


    public static void warn(String format, Exception e) {
        log.warn(format, e);
    }

    public static void warn(String msg) {
        log.warn(msg);
    }


}
