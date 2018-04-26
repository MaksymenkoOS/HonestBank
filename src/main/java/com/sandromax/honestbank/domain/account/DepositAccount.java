package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.account.history.History;

import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class DepositAccount extends Account {
    private double amount;
    private double depositRate;
    private LinkedList<History> replenishmentHistory;

//    ???
//    public boolean replenish(double sum) {
//
//        return true;
//    }

}
