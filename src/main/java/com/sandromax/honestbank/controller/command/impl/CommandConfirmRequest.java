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

public class CommandConfirmRequest implements Command {
    int newAccountRequestId;
    Logger logger = new FileLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.ADMIN_CABINET;

        collectAndCheckParams(request);

        createAccount(newAccountRequestId);

        setRequestAsActive(newAccountRequestId); // TODO: 13.05.18  

        createNewRequestList();

        setParams();

        return page;
    }

    private void collectAndCheckParams(HttpServletRequest request) {
        String param = (String) request.getParameter("request_id");
        newAccountRequestId = Integer.parseInt(param);
    }

    private void createAccount(int newAccountRequestId) {
        NewAccountRequestDao newAccountRequestDao = new NewAccountRequestDao(logger);
        NewAccountRequest newAccountRequest = newAccountRequestDao.findById(newAccountRequestId);

        if(newAccountRequest == null) {
            AccountType accountType = newAccountRequest.getAccountType();
            User user = newAccountRequest.getUser();
            Account newAccount = new Account(accountType, user);

            AccountDao accountDao = new AccountDao(logger);
            int id = accountDao.create(newAccount);
            if(id != 0)
                newAccount.setIdInDb(id);
        }
    }

    private void setRequestAsActive(int newAccountRequestId) {

    }

    private void createNewRequestList() {

    }

    private void setParams() {

    }


}
