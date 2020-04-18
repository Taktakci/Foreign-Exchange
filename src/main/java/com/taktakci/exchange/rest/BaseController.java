package com.taktakci.exchange.rest;

import com.taktakci.exchange.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {

    @ExceptionHandler({BaseException.class})
    public @ResponseBody Map handleMyException(BaseException ex) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("errorCode", ex.getError());
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }
}
