package com.taktakci.exchange.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CurrencyCodeLengthException extends RuntimeException {
    private final String currencyCode;

    private CurrencyCodeLengthException(String currencyCode) {this.currencyCode = currencyCode;}

    public static CurrencyCodeLengthException create(String currencyCode) {
        return new CurrencyCodeLengthException(currencyCode);
    }

    @Override
    public String getMessage() {
        return "Currency code lenght must be 3: " + currencyCode;
    }
}
