package com.taktakci.exchange.extcall;

import com.taktakci.exchange.logging.LogFactory;
import com.taktakci.exchange.logging.LogUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RatesApiUtil {

    private LogUtil logger = LogFactory.getLogger(this.getClass());

    double extractRate(ResponseEntity<String> apiResponse) {
        logger.info("extractRate() started");
        String responseBody = apiResponse.getBody();
        int numberEndIndex = responseBody.indexOf('}');
        String rateString = responseBody.substring(16, numberEndIndex);
        double rate = Double.parseDouble(rateString);
        logger.info("calculated rate:{}", rate);

        return rate;
    }

    HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "PostmanRuntime/7.24.0");
        return new HttpEntity<>("parameters", headers);
    }
}
