package com.taktakci.exchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "CONVERSION")
public class Conversion {
    @Id
    @GeneratedValue
    private Long transactionId;

    @Column(name = "DATE", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "SOURCE_CURRENCY", nullable = false)
    private String sourceCurrency;

    @Column(name = "TARGET_CURRENCY", nullable = false)
    private String targetCurrency;

    @Column(name = "RATE", nullable = false)
    private double rate;

    @Column(name = "SOURCE_AMOUNT", nullable = false)
    private double sourceAmount;

    @Column(name = "TARGET_AMOUNT", nullable = false)
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
}
