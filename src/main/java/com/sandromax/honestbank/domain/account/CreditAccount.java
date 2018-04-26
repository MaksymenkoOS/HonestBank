package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.account.history.PaymentHistory;

import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class CreditAccount extends Account {
    private double creditLimit;
    private double debt;
    private double accruedInterest;
    private double creditRate;
    private LinkedList<PaymentHistory> paymentHistory;


    //    ???
//    public boolean transfer(String recepient, double sum) {
//
//        return true;
//    }

//    ???
//    public boolean pay(String recepient, double sum) {
//
//        return true;
//    }

}
