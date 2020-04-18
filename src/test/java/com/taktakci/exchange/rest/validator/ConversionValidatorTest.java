package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.exception.DateFormatException;
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
        conversionValidator.validateConversionListParameters(1L, null);
    }

    @Test
    void validateConversionListParametersSucceedsWithDate() {
        conversionValidator.validateConversionListParameters(null, "2020-01-01");
    }

    @Test
    void validateConversionListParametersFailsWhenBothParametersAreNull() {
        Assertions.assertThrows(PathParamNullException.class,
                () -> conversionValidator.validateConversionListParameters(null, null));
    }

    @Test
    void validateConversionListParametersFailsWhenTransactioIdNegative() {
        Assertions.assertThrows(TransactionIdNegativeOrZeroException.class,
                () -> conversionValidator.validateConversionListParameters(-1L, null));
    }

    @Test
    void validateConversionListParametersFailsWhenDateFormatIsWrong() {
        Assertions.assertThrows(DateFormatException.class,
                () -> conversionValidator.validateConversionListParameters(null, "2020-02-1"));
    }

}
