package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.account.history.PaymentHistory;

import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class CreditAccount extends Account implements IAccount {
    private double creditLimit;
    private double debt;
    private double accruedInterest;
    private double creditRate;
    private LinkedList<PaymentHistory> paymentHistory;


    @Override
    public void transfer(String recepient, Double sum) {

    }

    @Override
    public void info() {

    }
}
