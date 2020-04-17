package com.taktakci.exchange.service.mapper;

import com.taktakci.exchange.dto.ExchangeRateResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateMapper {
    public ExchangeRateResponseDto toExchangeRateResponseDto(String sourceCurrency,
                                                             String targetCurrency,
                                                             double rate) {
        ExchangeRateResponseDto dto = new ExchangeRateResponseDto();
        dto.setSourceCurrency(sourceCurrency);
        dto.setTargetCurrency(targetCurrency);
        dto.setRate(rate);
        return dto;
    }
}
