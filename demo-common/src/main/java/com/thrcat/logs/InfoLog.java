package com.thrcat.logs;


import org.apache.log4j.Logger;

/**
 * @author scx
 */
public class InfoLog {

    static Logger log = Logger.getLogger(InfoLog.class);

    public static void info(String msg) {
        log.info(msg);
    }

    public static void info(String msg, Exception e) {
        log.info(msg, e);
    }


}
