package com.taktakci.exchange.dto;

public class ConversionRequestDto {

    private String sourceCurrency;
    private String targetCurrency;
    private Double sourceAmount;

    public ConversionRequestDto() {
    }

    public ConversionRequestDto(String sourceCurrency, String targetCurrency, Double sourceAmount) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceAmount = sourceAmount;
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

    public Double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    @Override
    public String toString()
    {
        return String.format("{sourceCurrency: %s, targetCurrency: %s, sourceAmount: %f}",
                this.sourceCurrency, this.targetCurrency, this.sourceAmount);
    }
}
