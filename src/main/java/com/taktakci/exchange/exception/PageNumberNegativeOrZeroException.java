package com.taktakci.exchange.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PageNumberNegativeOrZeroException extends RuntimeException {
    private final Long pageNumber;

    private PageNumberNegativeOrZeroException(Long pageNumber) {this.pageNumber = pageNumber;}

    public static PageNumberNegativeOrZeroException create(Long pageNumber) {
        return new PageNumberNegativeOrZeroException(pageNumber);
    }

    @Override
    public String getMessage() {
        return "pageNumber must be a positive number but found: " + pageNumber;
    }
}
