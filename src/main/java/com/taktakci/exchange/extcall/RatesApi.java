package com.taktakci.exchange.extcall;

import com.taktakci.exchange.exception.RatesApiException;
import com.taktakci.exchange.logging.LogFactory;
import com.taktakci.exchange.logging.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RatesApi implements FxProvider {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatesApiUtil ratesApiUtil;

    private LogUtil logger = LogFactory.getLogger(this.getClass());

    @Override
    public double getRate(String sourceCurrency, String targetCurrency) {

        logger.info("getRate() started");
        String uri = String.format("https://api.exchangeratesapi.io/api/latest?base=%s&symbols=%s",
                sourceCurrency.toUpperCase(), targetCurrency.toUpperCase());

        ResponseEntity<String> apiResponse;
        try {
            logger.info("rest call started, uri:{}", uri);
            apiResponse = restTemplate.exchange(uri, HttpMethod.GET, ratesApiUtil.getHttpEntity(), String.class);
        } catch (HttpClientErrorException.BadRequest e) {
            String errorMessage = e.getResponseBodyAsString();
            logger.error("rest call failed, errorMessage:{}", errorMessage);
            throw RatesApiException.create(errorMessage);
        }

        double rate = ratesApiUtil.extractRate(apiResponse);
        logger.info("rate calculated:{}", rate);

        return rate;
    }
}
