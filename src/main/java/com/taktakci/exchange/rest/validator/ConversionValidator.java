package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.dto.ConversionRequestDto;
import com.taktakci.exchange.exception.CurrencyCodeLengthException;
import com.taktakci.exchange.exception.DateFormatException;
import com.taktakci.exchange.exception.PageNumberNegativeOrZeroException;
import com.taktakci.exchange.exception.ParameterNullException;
import com.taktakci.exchange.exception.TransactionIdNegativeOrZeroException;
import com.taktakci.exchange.logging.LogFactory;
import com.taktakci.exchange.logging.LogUtil;
import org.springframework.stereotype.Service;

@Service
public class ConversionValidator {

    private LogUtil logger = LogFactory.getLogger(this.getClass());

    public void validateConversionListParameters(Long transactionId, String transactionDate, Integer page) {
        logger.info("validateConversionListParameters() started");
        if (transactionId == null && transactionDate == null) {
            logger.error("transactionId & transactionDate cannot be null at the same time");
            throw ParameterNullException.create("transactionId & transactionDate");
        }
        if (transactionId != null && transactionId <= 0) {
            logger.error("transactionId must be a positive number, transactionId={}", transactionId);
            throw TransactionIdNegativeOrZeroException.create(transactionId);
        }
        if (transactionDate != null && !transactionDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            logger.error("transactionDate input format error, transactionDate={}", transactionDate);
            throw DateFormatException.create(transactionDate);
        }
        if (page != null && page <= 0) {
            logger.error("page number must be a positive number, transactionId={}", transactionId);
            throw PageNumberNegativeOrZeroException.create(transactionId);
        }
        logger.info("validateConversionListParameters() successful");
    }

    public void validateConversionRequestParameters(ConversionRequestDto requestDto) {
        logger.info("validateConversionRequestParameters() started");
        if (requestDto.getSourceCurrency() == null) {
            logger.error("sourceCurrency cannot be null");
            throw ParameterNullException.create("sourceCurrency");
        }
        if (requestDto.getTargetCurrency() == null) {
            logger.error("targetCurrency cannot be null");
            throw ParameterNullException.create("targetCurrency");
        }
        if (requestDto.getSourceAmount() == null) {
            logger.error("sourceAmount cannot be null");
            throw ParameterNullException.create("sourceAmount");
        }
        if (requestDto.getSourceCurrency().length() != 3) {
            logger.error("sourceCurrency length must be 3 but found {} for {}",
                    requestDto.getSourceCurrency().length(), requestDto.getSourceCurrency());
            throw CurrencyCodeLengthException.create("sourceCurrency");
        }
        if (requestDto.getTargetCurrency().length() != 3) {
            logger.error("targetCurrency length must be 3 but found {} for {}",
                    requestDto.getTargetCurrency().length(), requestDto.getTargetCurrency());
            throw CurrencyCodeLengthException.create("targetCurrency");
        }
        logger.info("validateConversionRequestParameters() successful");
    }
}
