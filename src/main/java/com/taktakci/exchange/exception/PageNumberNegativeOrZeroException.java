package com.taktakci.exchange.exception;

public class PageNumberNegativeOrZeroException extends BaseException {

    private static final ErrorCode errorCode = ErrorCode.PAGE_NO_NEGATIVE_OR_ZERO;

    private PageNumberNegativeOrZeroException(Long pageNumber) {
        super(errorCode, pageNumber.toString());
    }

    public static PageNumberNegativeOrZeroException create(Long pageNumber) {
        return new PageNumberNegativeOrZeroException(pageNumber);
    }
}
