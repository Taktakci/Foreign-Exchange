package com.taktakci.exchange.rest.validator;

import com.taktakci.exchange.dto.ConversionRequestDto;
import com.taktakci.exchange.exception.CurrencyCodeLengthException;
import com.taktakci.exchange.exception.DateFormatException;
import com.taktakci.exchange.exception.PageNumberNegativeOrZeroException;
import com.taktakci.exchange.exception.ParameterNullException;
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
        Assertions.assertThrows(ParameterNullException.class,
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

    @Test
    void validateConversionRequestParametersSucceeds() {
        conversionValidator.validateConversionRequestParameters(
                new ConversionRequestDto("USD", "TRY",100d));
    }

    @Test
    void validateConversionRequestParametersFailsWhenSourceCurrencyIsNull() {
        Assertions.assertThrows(ParameterNullException.class,
                () -> conversionValidator.validateConversionRequestParameters
                        (new ConversionRequestDto(null, "TRY", 100d)));
    }

    @Test
    void validateConversionRequestParametersFailsWhenTargetCurrencyIsNull() {
        Assertions.assertThrows(ParameterNullException.class,
                () -> conversionValidator.validateConversionRequestParameters(
                        new ConversionRequestDto("USD", null, 100d)));
    }

    @Test
    void validateConversionRequestParametersFailsWhenSourceAmountIsNull() {
        Assertions.assertThrows(ParameterNullException.class,
                () -> conversionValidator.validateConversionRequestParameters(
                        new ConversionRequestDto("USD", "TRY", null)));
    }

    @Test
    void validateConversionRequestParametersFailsWhenSourceCurrencyLengthIsWrong() {
        Assertions.assertThrows(CurrencyCodeLengthException.class,
                () -> conversionValidator.validateConversionRequestParameters(
                        new ConversionRequestDto("US", "TRY", 100d)));
    }

    @Test
    void validateConversionRequestParametersFailsWhenTargetCurrencyLengthIsWrong() {
        Assertions.assertThrows(CurrencyCodeLengthException.class,
                () -> conversionValidator.validateConversionRequestParameters(
                        new ConversionRequestDto("USD", "TRY1", 100d)));
    }

}
