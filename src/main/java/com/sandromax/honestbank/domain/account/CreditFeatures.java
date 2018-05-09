package com.sandromax.honestbank.domain.account;

public class CreditFeatures {

    private int accountId;
    private double creditLimit;
    private double accruedInterest;


    public CreditFeatures() {
    }

    public CreditFeatures(double creditLimit, double accruedInterest) {
        this.creditLimit = creditLimit;
        this.accruedInterest = accruedInterest;
    }

    public CreditFeatures(int accountId, double creditLimit, double accruedInterest) {
        this.accountId = accountId;
        this.creditLimit = creditLimit;
        this.accruedInterest = accruedInterest;
    }


    public double getCreditLimit() {
        return creditLimit;
    }

    public double getAccruedInterest() {
        return accruedInterest;
    }

    public int getAccountId() {
        return accountId;
    }


    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setAccruedInterest(double accruedInterest) {
        this.accruedInterest = accruedInterest;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
