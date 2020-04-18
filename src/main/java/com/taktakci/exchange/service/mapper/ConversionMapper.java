package com.taktakci.exchange.service.mapper;

import com.taktakci.exchange.dto.ConversionRequestDto;
import com.taktakci.exchange.dto.ConversionResponseDto;
import com.taktakci.exchange.entity.Conversion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversionMapper {
    public ConversionResponseDto toConversionResponseDto(Conversion conversion) {
        ConversionResponseDto dto = new ConversionResponseDto();
        if (conversion != null) {
            dto.setSourceAmount(conversion.getSourceAmount());
            dto.setTargetAmount(conversion.getTargetAmount());
            dto.setRate(conversion.getRate());
            dto.setTransactionDate(conversion.getTransactionDate());
            dto.setTransactionId(conversion.getTransactionId());
            dto.setSourceCurrency(conversion.getSourceCurrency());
            dto.setTargetCurrency(conversion.getTargetCurrency());
        }

        return dto;
    }

    public List<ConversionResponseDto> toConversionResponseDtoList(List<Conversion> conversionList) {

        return conversionList.stream()
                .map(this::toConversionResponseDto)
                .collect(Collectors.toList());
    }

    public List<ConversionResponseDto> toConversionResponseDtoList(Conversion conversion) {

        List<Conversion> conversionList = new ArrayList<>();
        conversionList.add(conversion);
        return toConversionResponseDtoList(conversionList);
    }

    public Conversion toConversion(ConversionRequestDto requestDto) {
        Conversion conversion = new Conversion();
        if (requestDto != null) {
            conversion.setSourceAmount(requestDto.getSourceAmount());
            conversion.setSourceCurrency(requestDto.getSourceCurrency());
            conversion.setTargetCurrency(requestDto.getTargetCurrency());
        }

        return conversion;
    }
}
