package com.taktakci.exchange.logging;

public class LogFactory {

    public static LogUtil getLogger(Class clazz) {
        return new LogUtilImpl(clazz);
    }
}
