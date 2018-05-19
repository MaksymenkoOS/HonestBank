package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.AccountDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

public class CommandDepositAccount implements Command {

    User user;
    AccountType accountType = AccountType.DEPOSIT;
    AccountDao accountDao;
    HttpSession session;
    Logger logger = new FileLogger();
    LinkedList<Account> accounts;

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.DEPOSIT_ACCOUNT;

//        HttpSession session = request.getSession();
//        session.setAttribute("account_type", "deposit");

        init(request);
        getAccountInfo();
        setParams(request);

        return page;
    }

    private void init(HttpServletRequest request) {
        session = request.getSession();
        user = (User) session.getAttribute("user");

        if(user == null) {
            logger.log("Error! Empty param 'user'");
            request.setAttribute("message", "Error! Empty param 'user'. Please reenter.");
            return;
        }

        accountDao = new AccountDao(logger);

    }

    private void getAccountInfo() {
        accounts = accountDao.findByUserAndAccountType(user, accountType);
    }

    private void setParams(HttpServletRequest request) {
        session.setAttribute("account_type", "deposit");
        request.setAttribute("accounts", accounts);

    }
}
