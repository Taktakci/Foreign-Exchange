package com.taktakci.exchange.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class TransactionIdNegativeOrZeroException extends RuntimeException {
    private final Long transactionId;

    private TransactionIdNegativeOrZeroException(Long transactionId) {this.transactionId = transactionId;}

    public static TransactionIdNegativeOrZeroException create(Long transactionId) {
        return new TransactionIdNegativeOrZeroException(transactionId);
    }

    @Override
    public String getMessage() {
        return "transactionId must be a positive number but found: " + transactionId;
    }
}
