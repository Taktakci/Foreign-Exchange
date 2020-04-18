package com.taktakci.exchange.service;

import com.taktakci.exchange.dto.ExchangeRateResponseDto;
import com.taktakci.exchange.extcall.FxProvider;
import com.taktakci.exchange.logging.LogFactory;
import com.taktakci.exchange.logging.LogUtil;
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

    private LogUtil logger = LogFactory.getLogger(this.getClass());

    public ExchangeRateResponseDto getRate(String sourceCurrency, String targetCurrency) {

        logger.info("getRate() called with parameters, sourceCurrency={}, targetCurrency={}",
                sourceCurrency, targetCurrency);
        exchangeRateValidator.validateCurrencies(sourceCurrency, targetCurrency);

        double rate = fxProvider.getRate(sourceCurrency, targetCurrency);
        logger.info("fxProvider gets exchange rate={}", rate);

        ExchangeRateResponseDto dto = exchangeRateMapper.toExchangeRateResponseDto(sourceCurrency, targetCurrency, rate);

        logger.info("getRate() returns, dto={}", dto);
        return dto;
    }

}
