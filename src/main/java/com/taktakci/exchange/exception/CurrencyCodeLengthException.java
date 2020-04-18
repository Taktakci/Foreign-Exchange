package com.taktakci.exchange.exception;

public class CurrencyCodeLengthException extends BaseException {

    private static final ErrorCode errorCode = ErrorCode.CURRENCY_CODE_LEN;

    private CurrencyCodeLengthException(String currencyCode) {
        super(errorCode, currencyCode);
    }

    public static CurrencyCodeLengthException create(String currencyCode) {
        return new CurrencyCodeLengthException(currencyCode);
    }
}
