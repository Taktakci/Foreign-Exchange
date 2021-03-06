package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.exception.CurrencyCodeLengthException;
import com.taktakci.exchange.exception.ParameterNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExchangeRateValidatorTest {

    @Autowired
    private ExchangeRateValidator exchangeRateValidator;

    @Test
    void validateCurrenciesSuccessful() {
        exchangeRateValidator.validateCurrencies("USD", "GBP");
    }

    @Test
    void validateCurrenciesFailsWhenSourceIsNull() {
        Assertions.assertThrows(ParameterNullException.class,
                () -> exchangeRateValidator.validateCurrencies(null, "GBP"));
    }

    @Test
    void validateCurrenciesFailsWhenTargetIsNull() {
        Assertions.assertThrows(ParameterNullException.class,
                () -> exchangeRateValidator.validateCurrencies("USD", null));
    }

    @Test
    void validateCurrenciesFailsWhenSourceIsEmptyString() {
        Assertions.assertThrows(CurrencyCodeLengthException.class,
                () -> exchangeRateValidator.validateCurrencies("", "GBP"));
    }

    @Test
    void validateCurrenciesFailsWhenTargetIsEmptyString() {
        Assertions.assertThrows(CurrencyCodeLengthException.class,
                () -> exchangeRateValidator.validateCurrencies("USD", ""));
    }

    @Test
    void validateCurrenciesFailsWhenSourceLengthIsNotThree() {
        Assertions.assertThrows(CurrencyCodeLengthException.class,
                () -> exchangeRateValidator.validateCurrencies("US", "GBP"));
    }

    @Test
    void validateCurrenciesFailsWhenTargetLengthIsNotThree() {
        Assertions.assertThrows(CurrencyCodeLengthException.class,
                () -> exchangeRateValidator.validateCurrencies("USD", "GBP2"));
    }

}