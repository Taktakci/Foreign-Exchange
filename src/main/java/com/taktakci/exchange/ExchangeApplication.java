package com.taktakci.exchange;

import com.taktakci.exchange.extcall.FxProvider;
import com.taktakci.exchange.extcall.RatesApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExchangeApplication {

    @Bean
    public FxProvider fxProvider() {
        return new RatesApi();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

}
