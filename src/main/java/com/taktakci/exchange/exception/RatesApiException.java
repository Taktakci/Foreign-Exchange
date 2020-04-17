package com.taktakci.exchange.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class RatesApiException extends RuntimeException {
    private final String errorMessage;

    private RatesApiException(String errorMessage) {this.errorMessage = errorMessage;}

    public static RatesApiException create(String errorMessage) {
        return new RatesApiException(errorMessage);
    }

    @Override
    public String getMessage() {
        return "RatesApi exception: " + errorMessage;
    }
}
