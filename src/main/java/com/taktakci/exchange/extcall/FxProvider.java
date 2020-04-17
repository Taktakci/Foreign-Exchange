package com.taktakci.exchange.extcall;

public interface FxProvider {
    public double getRate(String sourceCurrency, String targetCurrency);
}
