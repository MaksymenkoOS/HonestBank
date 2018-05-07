package com.sandromax.honestbank.domain.account;


import com.sandromax.honestbank.domain.user.User;

import java.time.LocalDate;

/**
 * Created by sandro on 26.04.18.
 */
public class CreditAccount extends Account implements IAccount {

    private CreditFeatures creditFeatures;
//    private double creditRate;
//    private LinkedList<PaymentHistory> paymentHistory;


    public CreditAccount() {
    }

    public CreditAccount(User user, double balance, LocalDate validityFrom, LocalDate validityTo, double rate, CreditFeatures creditFeatures) {
        super(AccountType.CREDIT, user, balance, validityFrom, validityTo, rate);
        this.creditFeatures = creditFeatures;
    }

    public CreditAccount(int idInDb, User user, double balance, LocalDate validityFrom, LocalDate validityTo, double rate, CreditFeatures creditFeatures) {
        super(idInDb,  AccountType.CREDIT, user, balance, validityFrom, validityTo, rate);
        this.creditFeatures = creditFeatures;
    }

    public CreditFeatures getCreditFeatures() {
        return creditFeatures;
    }

    public void setCreditFeatures(CreditFeatures creditFeatures) {
        this.creditFeatures = creditFeatures;
    }

    @Override
    public void transfer(Double sum, String recipient) {

    }

    @Override
    public void info() {

    }


}
