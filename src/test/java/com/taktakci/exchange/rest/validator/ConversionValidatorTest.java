package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.exception.DateFormatException;
import com.taktakci.exchange.exception.PageNumberNegativeOrZeroException;
import com.taktakci.exchange.exception.PathParamNullException;
import com.taktakci.exchange.exception.TransactionIdNegativeOrZeroException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConversionValidatorTest {

    @Autowired
    ConversionValidator conversionValidator;

    @Test
    void validateConversionListParametersSucceedsWithTransactionId() {
        conversionValidator.validateConversionListParameters(1L, null, null);
    }

    @Test
    void validateConversionListParametersSucceedsWithDate() {
        conversionValidator.validateConversionListParameters(null, "2020-01-01", null);
    }

    @Test
    void validateConversionListParametersFailsWhenBothParametersAreNull() {
        Assertions.assertThrows(PathParamNullException.class,
                () -> conversionValidator.validateConversionListParameters(null, null, null));
    }

    @Test
    void validateConversionListParametersFailsWhenTransactionIdNegative() {
        Assertions.assertThrows(TransactionIdNegativeOrZeroException.class,
                () -> conversionValidator.validateConversionListParameters(-1L, null, null));
    }

    @Test
    void validateConversionListParametersFailsWhenDateFormatIsWrong() {
        Assertions.assertThrows(DateFormatException.class,
                () -> conversionValidator.validateConversionListParameters(null, "2020-02-1", null));
    }

    @Test
    void validateConversionListParametersFailsWhenPageNumberNegative() {
        Assertions.assertThrows(PageNumberNegativeOrZeroException.class,
                () -> conversionValidator.validateConversionListParameters(1L, null, -1));
    }

}
