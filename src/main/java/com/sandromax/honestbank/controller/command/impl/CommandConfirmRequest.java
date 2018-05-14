package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.AccountDao;
import com.sandromax.honestbank.model.dao.impl.NewAccountRequestDao;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

public class CommandConfirmRequest implements Command {
    int newAccountRequestId;
    Logger logger = new FileLogger();
    NewAccountRequestDao newAccountRequestDao;
    NewAccountRequest newAccountRequest;

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.ADMIN_CABINET;

        collectAndCheckParams(request);

        createAccount(newAccountRequestId);

        setRequestAsActive(newAccountRequest); // TODO: 13.05.18  Ask: How it looks??

        setParams(request, createNewRequestList(), getAllActiveAccounts()); // TODO: 14.05.18 ASK: How it looks?

        return page;
    }

    private void collectAndCheckParams(HttpServletRequest request) {
        String param = request.getParameter("request_id");
        if(param.length() != 0)
            newAccountRequestId = Integer.parseInt(param);
        else {
            logger.log("Error! Empty param field ('request_id')");
            request.setAttribute("message", "Error! Empty param field ('request_id')");
        }
    }

    private void createAccount(int newAccountRequestId) {
        try {
            newAccountRequestDao = new NewAccountRequestDao(logger);
            newAccountRequest = newAccountRequestDao.findById(newAccountRequestId);

            if(newAccountRequest != null) {
                AccountType accountType = newAccountRequest.getAccountType();
                User user = newAccountRequest.getUser();
                Account newAccount = new Account(accountType, user);

                AccountDao accountDao = new AccountDao(logger);
                if(accountDao.findByUserAndAccountType(user, accountType).size() == 0) {
                    int id = accountDao.create(newAccount);
                    if(id != 0)
                        newAccount.setIdInDb(id);
                }
            }
        } catch (Exception e) {
            // TODO: 14.05.18 DaoException
        }
    }

    private void setRequestAsActive(NewAccountRequest accountRequest) {
//        accountRequest.setConfirmed(true);
        newAccountRequestDao = new NewAccountRequestDao(logger);
        newAccountRequestDao.setConfirmedById(newAccountRequestId);
    }

    private LinkedList<NewAccountRequest> createNewRequestList() {
        NewAccountRequestDao dao = new NewAccountRequestDao(new FileLogger());
        return dao.findAllNotConfirmed();
    }

    private LinkedList<Account> getAllActiveAccounts() {
        AccountDao accountDao = new AccountDao(logger);
        return accountDao.findAll();
    }

    private void setParams(HttpServletRequest request, LinkedList<NewAccountRequest> newAccountRequests, LinkedList<Account> activeAccounts) {
        request.setAttribute("requests", newAccountRequests);
        request.setAttribute("active_accounts", activeAccounts);
    }

}
