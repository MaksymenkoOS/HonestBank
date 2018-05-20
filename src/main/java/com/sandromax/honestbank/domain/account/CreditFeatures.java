package com.sandromax.honestbank.domain.account;

public class CreditFeatures {

    private int accountId;
    private double creditLimit;
    private double indebtedness;

    /**
     * Constructors
     */
    public CreditFeatures() {
    }

    public CreditFeatures(int accountId, double creditLimit) {
        this.accountId = accountId;
        this.creditLimit = creditLimit;
    }

    public CreditFeatures(int accountId, double creditLimit, double indebtedness) {
        this.accountId = accountId;
        this.creditLimit = creditLimit;
        this.indebtedness = indebtedness;
    }


    /**
     * Getters
     */
    public int getAccountId() {
        return accountId;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getIndebtedness() {
        return indebtedness;
    }

    /**
     * Setters
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setIndebtedness(double indebtedness) {
        this.indebtedness = indebtedness;
    }
}
