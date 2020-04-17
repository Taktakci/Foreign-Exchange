package com.taktakci.exchange.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PathParamNullException extends RuntimeException {
    private final String inputName;

    private PathParamNullException(String inputName) {this.inputName = inputName;}

    public static PathParamNullException create(String inputName) {
        return new PathParamNullException(inputName);
    }

    @Override
    public String getMessage() {
        return "Input can't be null: " + inputName;
    }
}
