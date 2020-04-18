package com.taktakci.exchange.exception;

public class RatesApiException extends BaseException {

    private static final ErrorCode errorCode = ErrorCode.REST_API;

    private RatesApiException(String errorMessage) {
        super(errorCode, errorMessage);
    }

    public static RatesApiException create(String errorMessage) {
        return new RatesApiException(errorMessage);
    }
}
