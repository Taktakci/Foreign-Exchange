package com.taktakci.exchange.exception;

public class TransactionIdNegativeOrZeroException extends BaseException {

    private static final ErrorCode errorCode = ErrorCode.TRANSACTION_ID_NEGATIVE_OR_ZERO;

    private TransactionIdNegativeOrZeroException(Long transactionId) {
        super(errorCode, transactionId.toString());
    }

    public static TransactionIdNegativeOrZeroException create(Long transactionId) {
        return new TransactionIdNegativeOrZeroException(transactionId);
    }
}
