package com.taktakci.exchange.exception;

public class PathParamNullException extends BaseException {

    private static final ErrorCode errorCode = ErrorCode.PATH_PARAM_NULL;

    private PathParamNullException(String messageDetail) {
        super(errorCode, messageDetail);
    }

    public static PathParamNullException create(String inputName) {
        return new PathParamNullException(inputName);
    }
}
