package com.taktakci.exchange.rest;


import com.taktakci.exchange.dto.ExchangeRateResponseDto;
import com.taktakci.exchange.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/rate")
public class ExchangeRateController extends BaseController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping(value = "")
    public ExchangeRateResponseDto get(@PathParam("sourceCurrency") String sourceCurrency,
                                       @PathParam("targetCurrency") String targetCurrency) {

        return exchangeRateService.getRate(sourceCurrency, targetCurrency);
    }
}
