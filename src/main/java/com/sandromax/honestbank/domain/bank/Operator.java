package com.sandromax.honestbank.domain.bank;

import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.CreditAccount;
import com.sandromax.honestbank.domain.account.DepositAccount;
import com.sandromax.honestbank.domain.user.User;

/**
 * Created by sandro on 26.04.18.
 */
public class Operator {
    private Account account;

    public boolean requestForOpeningAnAccount() {

        return true;
    }

    public boolean responseToRequest() {

        return true;
    }
}
