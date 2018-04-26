package com.sandromax.honestbank.domain.bank;

import com.sandromax.honestbank.domain.account.CreditAccount;
import com.sandromax.honestbank.domain.account.DepositAccount;
import com.sandromax.honestbank.domain.user.User;

/**
 * Created by sandro on 26.04.18.
 */
public class Operator {
    public static boolean transfer(CreditAccount account, User user, String recepient) {

        return true;
    }

    public static boolean pay(CreditAccount account, User user, String recepient) {

        return true;
    }

    public static boolean replenish(DepositAccount account, User user) {

        return true;
    }

    public static boolean requestForOpeningAnAccount(String accountType, User user) {

        return true;
    }

    public static boolean responseToRequest() {

        return true;
    }
}
