package com.taktakci.exchange.repository;

import com.taktakci.exchange.entity.Conversion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ConversionRepositoryTest {

    @Autowired
    ConversionRepository conversionRepository;

    @Test
    void findByIdFindsConversion() {
        Conversion conversion = conversionRepository.findById(1001L);

        assertNotNull(conversion);
        assertEquals("USD", conversion.getSourceCurrency());
        assertEquals("TRY", conversion.getTargetCurrency());
        assertEquals(6.9347905952, conversion.getRate());
    }

    @Test
    void findByTransactionDateFindsConversionList() {
        List<Conversion> conversionList = conversionRepository.findByTransactionDate("2020-04-11");

        assertNotNull(conversionList);
        assertEquals(2, conversionList.size());
    }

    @Test
    void findAllFindsSixConversions() {
        List<Conversion> conversionList = conversionRepository.findAll();

        assertNotNull(conversionList);
        assertEquals(6, conversionList.size());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        Conversion conversion = conversionRepository.findById(1001L);
        assertNotNull(conversion);

        conversionRepository.deleteById(1001L);

        conversion = conversionRepository.findById(1001L);
        assertNull(conversion);
    }

    @Test
    @DirtiesContext
    void saveUpdatesAnAvailableConversion() {
        Conversion conversionToBeUpdated = conversionRepository.findById(1001L);
        assertNotNull(conversionToBeUpdated);

        double newTargetAmount = conversionToBeUpdated.getTargetAmount() + 100L;
        conversionToBeUpdated.setTargetAmount(newTargetAmount);
        conversionRepository.save(conversionToBeUpdated);

        Conversion conversion = conversionRepository.findById(1001L);
        assertNotNull(conversion);
        assertEquals(newTargetAmount, conversion.getTargetAmount());
    }


    @Test
    @DirtiesContext
    void savePersistsANewConversion() {
        Conversion conversion = new Conversion();
        conversion.setSourceAmount(100);
        conversion.setTargetAmount(200);
        conversion.setRate(2.0);
        conversion.setTransactionDate(LocalDate.now());
        conversion.setSourceCurrency("USD");
        conversion.setTargetCurrency("GBP");

        conversionRepository.save(conversion);
        Long transactionId = conversion.getTransactionId();
        assertNotNull(transactionId);

        List<Conversion> conversionList = conversionRepository.findAll();
        assertNotNull(conversionList);
        assertEquals(7, conversionList.size());
    }
}