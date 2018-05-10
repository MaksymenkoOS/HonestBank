package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.user.User;

/**
 * Describes request to open new account from user to admin
 * @author Maksymenko Oleksandr
 */
public class NewAccountRequest {
    private int idInDb;
    private AccountType accountType;
    private User user;

    // TODO: 10.05.18 Ask: how to describe constructors and getter/setter in comments
    public NewAccountRequest() {
    }

    public NewAccountRequest(AccountType accountType, User user) {
        this.accountType = accountType;
        this.user = user;
    }

    public NewAccountRequest(int idInDb, AccountType accountType, User user) {
        this.idInDb = idInDb;
        this.accountType = accountType;
        this.user = user;
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

    public void setIdInDb(int idInDb) {
        this.idInDb = idInDb;
    }
}
