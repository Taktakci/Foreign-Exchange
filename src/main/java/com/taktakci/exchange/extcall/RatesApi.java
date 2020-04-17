package com.taktakci.exchange.extcall;

import com.taktakci.exchange.exception.RatesApiException;
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

    @Override
    public double getRate(String sourceCurrency, String targetCurrency) {

        String uri = String.format("https://api.exchangeratesapi.io/api/latest?base=%s&symbols=%s",
                sourceCurrency.toUpperCase(), targetCurrency.toUpperCase());

        ResponseEntity<String> apiResponse;
        try {
            apiResponse = restTemplate.exchange(uri, HttpMethod.GET, ratesApiUtil.getHttpEntity(), String.class);
        } catch (HttpClientErrorException.BadRequest e) {
            String errorMessage = e.getResponseBodyAsString();
            throw RatesApiException.create(errorMessage);
        }

        return ratesApiUtil.extractRate(apiResponse);
    }
}
