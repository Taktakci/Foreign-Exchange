package com.taktakci.exchange.exception;

public enum ErrorCode {
    PATH_PARAM_NULL("0001", "Input can't be null: "),
    CURRENCY_CODE_LEN("0002", "Currency code lenght must be 3: "),
    DATE_FORMAT("0003", "Date format must be yyyy-mm-dd but found: "),
    PAGE_NO_NEGATIVE_OR_ZERO("0004", "pageNumber must be a positive number but found: "),
    REST_API("0005", "RatesApi exception: "),
    TRANSACTION_ID_NEGATIVE_OR_ZERO("0006", "transactionId must be a positive number but found: ");

    private final String code;
    private final String description;

    private ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}
