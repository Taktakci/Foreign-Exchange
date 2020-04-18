package com.taktakci.exchange.logging;

public interface LogUtil {
    public void info(String message);
    public void info(String message, Object... objects);
    public void error(String message);
    public void error(String message, Object... objects);
}
