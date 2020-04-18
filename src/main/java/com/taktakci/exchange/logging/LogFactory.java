package com.taktakci.exchange.logging;

public class LogFactory {

    private LogFactory() { }

    public static LogUtil getLogger(Class clazz) {
        return new LogUtilImpl(clazz);
    }
}
