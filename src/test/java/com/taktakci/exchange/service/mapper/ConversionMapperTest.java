package com.taktakci.exchange.service.mapper;

import com.taktakci.exchange.dto.ConversionResponseDto;
import com.taktakci.exchange.entity.Conversion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConversionMapperTest {

    @Autowired
    private ConversionMapper conversionMapper;

    @Test
    void toConversionResponseDto() {
        Conversion conversion = new Conversion();
        conversion.setSourceAmount(100);
        conversion.setTargetAmount(200);
        conversion.setRate(2.0);
        LocalDate now = LocalDate.now();
        conversion.setTransactionDate(now);
        conversion.setTransactionId(123L);
        conversion.setSourceCurrency("USD");
        conversion.setTargetCurrency("GBP");

        ConversionResponseDto dto = conversionMapper.toConversionResponseDto(conversion);

        assertNotNull(dto);
        assertEquals(100, dto.getSourceAmount());
        assertEquals(200, dto.getTargetAmount());
        assertEquals(2.0, dto.getRate());
        assertEquals(now, dto.getTransactionDate());
        assertEquals(123L, dto.getTransactionId());
        assertEquals("USD", dto.getSourceCurrency());
        assertEquals("GBP", dto.getTargetCurrency());
    }

    @Test
    void toConversionResponseDtoList() {
        Conversion conversion = new Conversion();
        conversion.setSourceAmount(100);
        conversion.setTargetAmount(200);
        conversion.setRate(2.0);
        LocalDate now = LocalDate.now();
        conversion.setTransactionDate(now);
        conversion.setTransactionId(123L);
        conversion.setSourceCurrency("USD");
        conversion.setTargetCurrency("GBP");
        List<Conversion> conversionList = new ArrayList<>();
        conversionList.add(conversion);

        List<ConversionResponseDto> dtoList = conversionMapper.toConversionResponseDtoList(conversionList);

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());

        ConversionResponseDto dto = dtoList.get(0);
        assertNotNull(dto);
        assertEquals(100, dto.getSourceAmount());
        assertEquals(200, dto.getTargetAmount());
        assertEquals(2.0, dto.getRate());
        assertEquals(now, dto.getTransactionDate());
        assertEquals(123L, dto.getTransactionId());
        assertEquals("USD", dto.getSourceCurrency());
        assertEquals("GBP", dto.getTargetCurrency());
    }
}