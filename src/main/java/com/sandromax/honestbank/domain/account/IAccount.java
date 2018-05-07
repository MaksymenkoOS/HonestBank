package com.sandromax.honestbank.domain.account;

/**
 * Created by sandro on 01.05.18.
 */
public interface IAccount {

    void transfer(Double sum, String recipient);

    void info();
}
