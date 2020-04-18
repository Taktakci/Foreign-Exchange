package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.exception.DateFormatException;
import com.taktakci.exchange.exception.PageNumberNegativeOrZeroException;
import com.taktakci.exchange.exception.PathParamNullException;
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
            throw PathParamNullException.create("transactionId & transactionDate");
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

}
