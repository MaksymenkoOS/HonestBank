package com.sandromax.honestbank.domain.account;


import com.sandromax.honestbank.domain.user.User;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class DepositAccount extends Account implements IAccount {
//    private double balance;
//    private double depositRate;
//    private LinkedList<History> replenishmentHistory;


    public DepositAccount() {
    }

    public DepositAccount(User user, double balance, LocalDate validityFrom, LocalDate validityTo, double rate) {
        super(AccountType.DEPOSIT, user, balance, validityFrom, validityTo, rate);
    }

    public DepositAccount(int idInDb, User user, double balance, LocalDate validityFrom, LocalDate validityTo, double rate) {
        super(idInDb, AccountType.DEPOSIT, user, balance, validityFrom, validityTo, rate);
    }

    @Override
    public void transfer(Double sum, String recipient) {

    }

    @Override
    public void info() {

    }
}
