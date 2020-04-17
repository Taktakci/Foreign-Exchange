package com.taktakci.exchange.service;

import com.taktakci.exchange.dto.ExchangeRateResponseDto;
import com.taktakci.exchange.extcall.FxProvider;
import com.taktakci.exchange.rest.validator.ExchangeRateValidator;
import com.taktakci.exchange.service.mapper.ExchangeRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateValidator exchangeRateValidator;

    @Autowired
    private FxProvider fxProvider;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateResponseDto getRate(String sourceCurrency, String targetCurrency) {

        exchangeRateValidator.validateCurrencies(sourceCurrency, targetCurrency);

        double rate = fxProvider.getRate(sourceCurrency, targetCurrency);

        return exchangeRateMapper.toExchangeRateResponseDto(sourceCurrency, targetCurrency, rate);
    }

}
