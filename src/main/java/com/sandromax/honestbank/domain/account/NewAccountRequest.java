package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.user.User;

import java.time.LocalDate;

/**
 * Describes request to open new account from user to admin
 * @author Maksymenko Oleksandr
 */
public class NewAccountRequest {
    private int idInDb;
    private AccountType accountType;
    private User user;
    private LocalDate date;
    private boolean isAccepted;
    private boolean isDeclined;
    private double rate;
    private double limit;

    // TODO: 10.05.18 Ask: how to describe constructors and getter/setter in comments
    public NewAccountRequest() {
    }

    public NewAccountRequest(AccountType accountType, User user, double rate, double limit) {
        this.accountType = accountType;
        this.user = user;
        this.rate = rate;
        this.limit = limit;
    }

    public NewAccountRequest(int idInDb, AccountType accountType, User user, LocalDate date, boolean isAccepted, boolean isDeclined, double rate, double limit) {
        this.idInDb = idInDb;
        this.accountType = accountType;
        this.user = user;
        this.date = date;
        this.isAccepted = isAccepted;
        this.isDeclined = isDeclined;
        this.rate = rate;
        this.limit = limit;
    }

    /**
     * Getters
     * @return
     */
    public int getIdInDb() {
        return idInDb;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public boolean isDeclined() {
        return isDeclined;
    }

    public double getRate() {
        return rate;
    }

    public double getLimit() {
        return limit;
    }

    /**
     * Setters
     * @param idInDb
     */
    public void setIdInDb(int idInDb) {
        this.idInDb = idInDb;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public void setDeclined(boolean declined) {
        isDeclined = declined;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
