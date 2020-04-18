package com.taktakci.exchange.exception;

public abstract class BaseException extends RuntimeException {

    private final ErrorCode errCode;
    private final String messageDetail;

    public BaseException(ErrorCode errorCode, String messageDetail) {
        this.errCode = errorCode;
        this.messageDetail = messageDetail;
    }

    @Override
    public String getMessage() {
        return errCode.getDescription() + messageDetail;
    }

    public String getError() {
        return errCode.getCode();
    }
}
