package com.sandromax.honestbank.domain.account;


/**
 * Created by sandro on 26.04.18.
 */
public class CreditAccount extends Account implements IAccount {

    private double creditLimit;
    private double accruedInterest;
//    private double creditRate;
//    private LinkedList<PaymentHistory> paymentHistory;


    @Override
    public void transfer(Double sum, String recepient) {

    }

    @Override
    public void info() {

    }


}
