package com.taktakci.exchange.extcall;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RatesApiUtil {
    double extractRate(ResponseEntity<String> apiResponse) {
        String responseBody = apiResponse.getBody();
        int numberEndIndex = responseBody.indexOf('}');
        String rateString = responseBody.substring(16, numberEndIndex);
        return Double.parseDouble(rateString);
    }

    HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "PostmanRuntime/7.24.0");
        return new HttpEntity<>("parameters", headers);
    }
}
