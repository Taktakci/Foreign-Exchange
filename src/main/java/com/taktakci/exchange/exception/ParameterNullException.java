package com.taktakci.exchange.exception;

public class ParameterNullException extends BaseException {

    private static final ErrorCode errorCode = ErrorCode.PARAMETER_NULL;

    private ParameterNullException(String messageDetail) {
        super(errorCode, messageDetail);
    }

    public static ParameterNullException create(String inputName) {
        return new ParameterNullException(inputName);
    }
}
