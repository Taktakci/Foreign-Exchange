package com.taktakci.exchange.extcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RatesApiUtilTest {

    @Autowired
    private RatesApiUtil ratesApiUtil;

    @Test
    void extractRate() {
        ResponseEntity<String> apiResponse =
                new ResponseEntity<>("{\"rates\":{\"GBP\":0.800541881},\"base\":\"USD\",\"date\":\"2020-04-16\"}",
                        HttpStatus.valueOf(200));

        double rate = ratesApiUtil.extractRate(apiResponse);

        assertEquals(0.800541881, rate);
    }

    @Test
    void getHttpEntity() {
        HttpEntity<String> httpEntity = ratesApiUtil.getHttpEntity();

        assertNotNull(httpEntity);
    }
}