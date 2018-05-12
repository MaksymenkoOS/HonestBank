package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.AccountDao;
import com.sandromax.honestbank.model.dao.impl.NewAccountRequestDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

public class CommandNewAccount implements Command {

    String page = Pages.USER_CABINET;

    HttpSession session;

    String accountTypeParam;
    AccountType type;
    User user;

    FileLogger logger = new FileLogger();

    @Override
    public String execute(HttpServletRequest request) {
        session = request.getSession();

        collectParams();

        if(checkParamsAndReport(request)) {

            if(checkAndReport(type,user,request)) {
                NewAccountRequestDao dao = new NewAccountRequestDao(logger);
                dao.create(new NewAccountRequest(type, user));
            }
        }

        //  todo Ask: why don't work code below?
        System.out.println(request.getAttribute("account_type"));
        System.out.println(request.getParameter("account_type"));

        return page;
    }

    private void collectParams() {
        accountTypeParam = (String)session.getAttribute("account_type");
        type = AccountType.valueOf(accountTypeParam.toUpperCase());
        user = (User)session.getAttribute("user");
    }

    private boolean checkParamsAndReport(HttpServletRequest request) {
        if(accountTypeParam == null && accountTypeParam == "" && user == null) {
            logger.log("Sorry? Can't create new account. Empty params.");
            request.setAttribute("message", "Sorry? Can't create new account. Empty params.");
            return false;
        } else
            return true;
    }

    private boolean checkAndReport(AccountType type, User user, HttpServletRequest request) {
        if(checkNewAccountRequestsAndReport(type, user, request) && checkActiveAccountsAndReport(user, type, request))
            return true;
        else
            return false;
    }

    private boolean checkNewAccountRequestsAndReport(AccountType type, User user, HttpServletRequest request) {
        NewAccountRequestDao dao = new NewAccountRequestDao(logger);

        if(dao.isContainNotConfirmedByUserAndType(type, user)) {
            logger.log("Sorry? Can't create new account request. Request already in the list.");
            request.setAttribute("message", "Sorry? Can't create new account request. Request already in the list.");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkActiveAccountsAndReport(User user, AccountType type, HttpServletRequest request) {
        AccountDao dao = new AccountDao(this.logger);

        LinkedList<Account> accounts = dao.findByUserAndAccountType(user, type);

        if(accounts.size() > 0) {
            logger.log("Sorry? Can't create new account request. Account is active.");
            request.setAttribute("message", "Sorry? Can't create new account request. Account is active.");
            return false;
        }
        else {
            return true;
        }
    }
}
