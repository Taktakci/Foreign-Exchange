package com.taktakci.exchange.service;

import com.taktakci.exchange.dto.ConversionResponseDto;
import com.taktakci.exchange.entity.Conversion;
import com.taktakci.exchange.logging.LogFactory;
import com.taktakci.exchange.logging.LogUtil;
import com.taktakci.exchange.repository.ConversionRepository;
import com.taktakci.exchange.rest.validator.ConversionValidator;
import com.taktakci.exchange.service.mapper.ConversionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("convertService")
public class ConversionService {

    @Autowired
    private ConversionValidator conversionValidator;

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private ConversionMapper conversionMapper;

    private LogUtil logger = LogFactory.getLogger(this.getClass());

    public List<ConversionResponseDto> conversionList(Long transactionId, String transactionDate) {

        logger.info("conversionList() called with parameters, transactionId={}, transactionDate={}",
                transactionId, transactionDate);
        conversionValidator.validateConversionListParameters(transactionId, transactionDate);

        List<ConversionResponseDto> dtoList;
        if (transactionId != null) {
            logger.info("conversionList() calculated by transactionId");
            Conversion conversion = conversionRepository.findById(transactionId);
            dtoList = conversionMapper.toConversionResponseDtoList(conversion);
        } else {
            logger.info("conversionList() calculated by transactionDate");
            List<Conversion> conversionList = conversionRepository.findByTransactionDate(transactionDate);
            dtoList = conversionMapper.toConversionResponseDtoList(conversionList);
        }

        logger.info("conversionList() returns, dtoList={}", dtoList);
        return dtoList;
    }

    public List<ConversionResponseDto> conversionList() {

        logger.info("conversionList() called with no parameter");

        List<Conversion> conversionList = conversionRepository.findAll();
        List<ConversionResponseDto> dtoList = conversionMapper.toConversionResponseDtoList(conversionList);

        logger.info("conversionList() returns, dtoList={}", dtoList);
        return dtoList;
    }
}
