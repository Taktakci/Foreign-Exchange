package com.taktakci.exchange.dto;

import java.time.LocalDate;

public class ConversionResponseDto {

    private Long transactionId;
    private LocalDate transactionDate;
    private String sourceCurrency;
    private String targetCurrency;
    private double rate;
    private double sourceAmount;
    private double targetAmount;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    @Override
    public String toString()
    {
        return String.format("{transactionId: %s, transactionDate: %s, sourceCurrency: %s, targetCurrency: %s, rate: %s, sourceAmount: %s, targetAmount: %s}",
                this.transactionId, this.transactionDate, this.sourceCurrency, this.targetCurrency, this.rate, this.sourceAmount, this.targetAmount);
    }
}
