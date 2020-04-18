package com.taktakci.exchange.exception;

public class DateFormatException extends BaseException {

    private static final ErrorCode errorCode = ErrorCode.DATE_FORMAT;

    private DateFormatException(String dateFormat) {
        super(errorCode, dateFormat);
    }

    public static DateFormatException create(String dateFormat) {
        return new DateFormatException(dateFormat);
    }
}
