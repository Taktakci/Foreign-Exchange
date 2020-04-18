package com.taktakci.exchange.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DateFormatException extends RuntimeException {
    private final String dateFormat;

    private DateFormatException(String dateFormat) {this.dateFormat = dateFormat;}

    public static DateFormatException create(String dateFormat) {
        return new DateFormatException(dateFormat);
    }

    @Override
    public String getMessage() {
        return "Date format must be yyyy-mm-dd but found: " + dateFormat;
    }
}
