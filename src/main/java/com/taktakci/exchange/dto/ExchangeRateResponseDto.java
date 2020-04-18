package com.taktakci.exchange.dto;

public class ExchangeRateResponseDto {

    private String sourceCurrency;
    private String targetCurrency;
    private double rate;

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

    @Override
    public String toString()
    {
        return String.format("{sourceCurrency: %s, targetCurrency: %s, rate: %s}",
                this.sourceCurrency, this.targetCurrency, this.rate);
    }
}
