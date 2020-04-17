package com.taktakci.exchange.service.mapper;

import com.taktakci.exchange.dto.ExchangeRateResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeRateMapperTest {

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Test
    void toExchangeRateResponseDto() {

        ExchangeRateResponseDto dto =
                exchangeRateMapper.toExchangeRateResponseDto("USD", "TRY", 1.1);


        assertNotNull(dto);
        assertEquals("USD", dto.getSourceCurrency());
        assertEquals("TRY", dto.getTargetCurrency());
        assertEquals(1.1, dto.getRate());
    }
}