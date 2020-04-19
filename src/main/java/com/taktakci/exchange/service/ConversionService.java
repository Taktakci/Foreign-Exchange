package com.taktakci.exchange.service;

import com.taktakci.exchange.dto.ConversionRequestDto;
import com.taktakci.exchange.dto.ConversionResponseDto;
import com.taktakci.exchange.entity.Conversion;
import com.taktakci.exchange.logging.LogFactory;
import com.taktakci.exchange.logging.LogUtil;
import com.taktakci.exchange.repository.ConversionRepository;
import com.taktakci.exchange.rest.validator.ConversionValidator;
import com.taktakci.exchange.service.mapper.ConversionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("convertService")
public class ConversionService {

    @Autowired
    private ConversionValidator conversionValidator;

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private ConversionMapper conversionMapper;

    @Autowired
    private ExchangeRateService exchangeRateService;

    private LogUtil logger = LogFactory.getLogger(this.getClass());

    public List<ConversionResponseDto> getByTransactionOrByDate(Long transactionId, String transactionDate, Integer page) {

        logger.info("getByTransactionOrByDate() called with parameters, transactionId={}, transactionDate={}",
                transactionId, transactionDate);
        conversionValidator.validateConversionListParameters(transactionId, transactionDate, page);

        List<ConversionResponseDto> dtoList = findConversions(transactionId, transactionDate, page);

        logger.info("getByTransactionOrByDate() returns, dtoList={}", dtoList);
        return dtoList;
    }

    private List<ConversionResponseDto> findConversions(Long transactionId, String transactionDate, Integer page) {
        List<ConversionResponseDto> dtoList;
        if (transactionId != null) {
            logger.info("findConversions() calculated by transactionId");
            dtoList = findConversionByTransactionId(transactionId);
        } else {
            logger.info("findConversions() calculated by transactionDate");
            dtoList = findConversionByDate(transactionDate, page);
        }
        return dtoList;
    }

    private List<ConversionResponseDto> findConversionByDate(String transactionDate, Integer page) {
        List<ConversionResponseDto> dtoList;
        List<Conversion> conversionList = conversionRepository.findByTransactionDate(transactionDate, page);
        dtoList = conversionMapper.toConversionResponseDtoList(conversionList);
        return dtoList;
    }

    private List<ConversionResponseDto> findConversionByTransactionId(Long transactionId) {
        List<ConversionResponseDto> dtoList;
        Conversion conversion = conversionRepository.findById(transactionId);
        dtoList = conversionMapper.toConversionResponseDtoList(conversion);
        return dtoList;
    }

    public List<ConversionResponseDto> getAllConversions() {

        logger.info("conversionList() called with no parameter");

        List<Conversion> conversionList = conversionRepository.findAll();
        List<ConversionResponseDto> dtoList = conversionMapper.toConversionResponseDtoList(conversionList);

        logger.info("conversionList() returns, dtoList={}", dtoList);
        return dtoList;
    }

    public ConversionResponseDto calculateConversion(ConversionRequestDto requestDto) {

        logger.info("getConversion() called, requestDto:{}", requestDto);
        conversionValidator.validateConversionRequestParameters(requestDto);

        Conversion conversion = calculate(requestDto);

        ConversionResponseDto responseDto = conversionMapper.toConversionResponseDto(conversion);

        logger.info("getConversion() returns, responseDto={}", responseDto);
        return responseDto;
    }

    Conversion calculate(ConversionRequestDto requestDto) {
        double rate = exchangeRateService.getRate(requestDto.getSourceCurrency(), requestDto.getTargetCurrency())
                .getRate();
        double targetAmount = requestDto.getSourceAmount() * rate;

        Conversion conversion = conversionMapper.toConversion(requestDto);
        conversion.setTargetAmount(targetAmount);
        conversion.setRate(rate);
        conversion.setTransactionDate(LocalDate.now());
        conversionRepository.save(conversion);
        return conversion;
    }
}
