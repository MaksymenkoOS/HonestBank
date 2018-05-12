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
    private boolean isConfirmed;

    // TODO: 10.05.18 Ask: how to describe constructors and getter/setter in comments
    public NewAccountRequest() {
    }

    public NewAccountRequest(AccountType accountType, User user) {
        this.accountType = accountType;
        this.user = user;
    }

    public NewAccountRequest(int idInDb, AccountType accountType, User user, LocalDate date, boolean isConfirmed) {
        this.idInDb = idInDb;
        this.accountType = accountType;
        this.user = user;
        this.date = date;
        this.isConfirmed = isConfirmed;
    }


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

    public boolean isConfirmed() {
        return isConfirmed;
    }


    public void setIdInDb(int idInDb) {
        this.idInDb = idInDb;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
