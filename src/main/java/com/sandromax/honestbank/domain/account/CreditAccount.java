package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.account.history.PaymentHistory;

import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class CreditAccount extends Account implements IAccount {
    private double debt;
    private double creditLimit;
    private double accruedInterest;
    private double creditRate;
    private LinkedList<PaymentHistory> paymentHistory;


    @Override
    public void transfer(Double sum, String recepient) {

    }

    @Override
    public void info() {

    }
}
