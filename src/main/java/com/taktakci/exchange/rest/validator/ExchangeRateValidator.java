package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.exception.CurrencyCodeLengthException;
import com.taktakci.exchange.exception.PathParamNullException;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateValidator {

    public void validateCurrencies(String sourceCurrency, String targetCurrency) {
        if (sourceCurrency == null) {
            throw PathParamNullException.create("sourceCurrency");
        }
        if (targetCurrency == null) {
            throw PathParamNullException.create("targetCurrency");
        }
        if (sourceCurrency.length() != 3) {
            throw CurrencyCodeLengthException.create("sourceCurrency");
        }
        if (targetCurrency.length() != 3) {
            throw CurrencyCodeLengthException.create("targetCurrency");
        }
    }

}
