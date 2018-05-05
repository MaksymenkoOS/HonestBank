package com.sandromax.honestbank.domain.bank;

import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sandro on 26.04.18.
 */
public class Cabinet {
    private User user;
//    Account account;
    private Logger logger;

    public Cabinet() {
    }

    public Cabinet(User user, Logger logger) {
        this.user = user;
//        this.account = account;
        this.logger = logger;
    }

//    public static void info(Account account, User user, HttpServletRequest request) {
//    }
}
