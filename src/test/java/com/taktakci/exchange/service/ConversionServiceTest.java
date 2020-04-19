package com.taktakci.exchange.service;

import com.taktakci.exchange.dto.ConversionRequestDto;
import com.taktakci.exchange.dto.ExchangeRateResponseDto;
import com.taktakci.exchange.entity.Conversion;
import com.taktakci.exchange.repository.ConversionRepository;
import com.taktakci.exchange.rest.validator.ConversionValidator;
import com.taktakci.exchange.service.mapper.ConversionMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConversionServiceTest {

    @InjectMocks
    private ConversionService conversionService;

    @Mock
    private ConversionValidator conversionValidator;

    @Mock
    private ConversionRepository conversionRepository;

    @Mock
    private ConversionMapper conversionMapper;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Test
    void getByTransactionOrByDateGetsByTransactionId() {
        conversionService.getByTransactionOrByDate(123L, null, null);
        verify(conversionRepository, times(1)).findById(anyLong());
    }

    @Test
    void getByTransactionOrByDateGetsByDate() {
        conversionService.getByTransactionOrByDate(null, "2020-02-02", null);
        verify(conversionRepository, times(1)).findByTransactionDate(anyString(), any());
    }

    @Test
    void calculateFinds() {
        ConversionRequestDto requestDto = new ConversionRequestDto();
        requestDto.setSourceCurrency("USD");
        requestDto.setTargetCurrency("GBP");
        requestDto.setSourceAmount(100d);

        ExchangeRateResponseDto mockDto = new ExchangeRateResponseDto();
        mockDto.setRate(1.2);
        when(exchangeRateService.getRate(anyString(), anyString())).thenReturn(mockDto);
        when(conversionMapper.toConversion(any())).thenReturn(new Conversion());

        Conversion conversion = conversionService.calculate(requestDto);

        assertNotNull(conversion);
        assertEquals(120, conversion.getTargetAmount());
    }
}