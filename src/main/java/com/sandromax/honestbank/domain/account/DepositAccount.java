package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.account.history.History;

import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class DepositAccount extends Account implements IAccount {
//    private double balance;
    private double depositRate;
    private LinkedList<History> replenishmentHistory;


    @Override
    public void transfer(Double sum, String recepient) {

    }

    @Override
    public void info() {

    }
}
