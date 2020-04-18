package com.taktakci.exchange.rest;

import com.taktakci.exchange.dto.ConversionRequestDto;
import com.taktakci.exchange.dto.ConversionResponseDto;
import com.taktakci.exchange.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/conversion")
public class ConversionController {

    @Autowired
    private ConversionService convertService;

    @GetMapping("/all")
    public List<ConversionResponseDto> getAllConversions() {

        return convertService.getAllConversions();

    }

    @GetMapping
    public List<ConversionResponseDto> getByTransactionOrByDate(@PathParam("transactionId") Long transactionId,
                                                      @PathParam("transactionDate") String transactionDate) {

        return convertService.getByTransactionOrByDate(transactionId, transactionDate);

    }

    @PostMapping(value = "")
    public ConversionResponseDto calculateConversion(@RequestBody ConversionRequestDto conversionRequestDto) {
        return convertService.calculateConversion(conversionRequestDto);
    }
}
