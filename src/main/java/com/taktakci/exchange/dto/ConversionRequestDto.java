package com.taktakci.exchange.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConversionRequestDto {

    @NotNull
    @Size(min=3, max=3)
    private String sourceCurrency;
    @NotNull
    @Size(min=3, max=3)
    private String targetCurrency;
    @NotNull
    @Size(min=0)
    private double sourceAmount;

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

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    @Override
    public String toString()
    {
        return String.format("{sourceCurrency: %s, targetCurrency: %s, sourceAmount: %f}",
                this.sourceCurrency, this.targetCurrency, this.sourceAmount);
    }
}
