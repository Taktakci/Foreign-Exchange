package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.exception.CurrencyCodeLengthException;
import com.taktakci.exchange.exception.ParameterNullException;
import com.taktakci.exchange.logging.LogFactory;
import com.taktakci.exchange.logging.LogUtil;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateValidator {

    private LogUtil logger = LogFactory.getLogger(this.getClass());

    public void validateCurrencies(String sourceCurrency, String targetCurrency) {
        logger.info("validateCurrencies() started");
        if (sourceCurrency == null) {
            logger.error("sourceCurrency cannot be null");
            throw ParameterNullException.create("sourceCurrency");
        }
        if (targetCurrency == null) {
            logger.error("targetCurrency cannot be null");
            throw ParameterNullException.create("targetCurrency");
        }
        if (sourceCurrency.length() != 3) {
            logger.error("sourceCurrency length must be 3 but found {} for {}", sourceCurrency.length(), sourceCurrency);
            throw CurrencyCodeLengthException.create("sourceCurrency");
        }
        if (targetCurrency.length() != 3) {
            logger.error("targetCurrency length must be 3 but found {} for {}", sourceCurrency.length(), sourceCurrency);
            throw CurrencyCodeLengthException.create("targetCurrency");
        }
        logger.info("validateCurrencies() successful");
    }

}
