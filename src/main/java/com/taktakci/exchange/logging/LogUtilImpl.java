package com.taktakci.exchange.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtilImpl implements LogUtil {

    private Logger logger;

    public LogUtilImpl(Class clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void info(String message, Object... objects) {
        logger.info(message, objects);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Object... objects) {
        logger.error(message, objects);
    }
}
