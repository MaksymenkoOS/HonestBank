package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.account.CreditFeatures;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.AccountDao;
import com.sandromax.honestbank.model.dao.impl.CreditFeaturesDao;
import com.sandromax.honestbank.model.dao.impl.NewAccountRequestDao;
import com.sun.istack.internal.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

public class CommandAcceptRequest implements Command {

    int newAccountRequestId;

    Logger logger = new FileLogger();

    NewAccountRequestDao newAccountRequestDao;
    NewAccountRequest newAccountRequest;
    AccountType type;
    AccountDao accountDao;
    User user;
    
    CreditFeaturesDao creditFeaturesDao;

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.ADMIN_CABINET;

        collectParams(request);

        initVariables();

        if(checkPossibility()) {
            createAccount(newAccountRequestId);

            setRequestAsActive(newAccountRequest);

            setParams(request, createNewRequestList(), getAllActiveAccounts());
        }

        return page;
    }

    private void collectParams(HttpServletRequest request) {
        @NotNull String requestId = request.getParameter("request_id");
        if(requestId.length() != 0)
            newAccountRequestId = Integer.parseInt(requestId);
        else {
            logger.log("Error! Empty param field ('request_id')");
            request.setAttribute("message", "Error! Empty param field ('request_id')");
        }
    }

    private void initVariables() {
        try {
            newAccountRequestDao = new NewAccountRequestDao(logger);
            newAccountRequest = newAccountRequestDao.findById(newAccountRequestId);
            accountDao = new AccountDao(logger);
            user = newAccountRequest.getUser();
            type = newAccountRequest.getAccountType();
            creditFeaturesDao = new CreditFeaturesDao(logger);
        } catch (NullPointerException n) {
            logger.log(n.getMessage());
        } catch (Exception e) {
            logger.log(e.getMessage());
        }

    }

    private boolean checkPossibility() {
        int accountsByType = accountDao.findByUserAndAccountType(user, type).size();

        if(type.equals(AccountType.CREDIT)
                & accountsByType > 0) {
            return false;
        } else
            return true;
    }

    private void createAccount(int newAccountRequestId) {
        try {
//            newAccountRequestDao = new NewAccountRequestDao(logger);
//            newAccountRequest = newAccountRequestDao.findById(newAccountRequestId);

            if(newAccountRequest != null) {
                double rate = newAccountRequest.getRate();
                double limit = newAccountRequest.getLimit();

                Account newAccount = new Account(type, user, 0.0, rate);// TODO: 20.05.18 Change constructor

                // TODO: 20.05.18 Need to lock tables(account, credit_features)
                int accountId = accountDao.create(newAccount);
                if(accountId != 0) {
                    newAccount.setIdInDb(accountId);

                    CreditFeatures creditFeatures = new CreditFeatures(accountId, limit);
                    creditFeaturesDao.create(creditFeatures);
                }


            }
        } catch (Exception e) {
            // TODO: 14.05.18 DaoException
        }
    }

    private void setRequestAsActive(NewAccountRequest accountRequest) {
//        accountRequest.setAccepted(true);
//        newAccountRequestDao = new NewAccountRequestDao(logger);
        newAccountRequestDao.setConfirmedById(newAccountRequestId);
    }

    private LinkedList<NewAccountRequest> createNewRequestList() {
//        NewAccountRequestDao dao = new NewAccountRequestDao(new FileLogger());
        return newAccountRequestDao.findAllNotConfirmed();
    }

    private LinkedList<Account> getAllActiveAccounts() {
//        AccountDao accountDao = new AccountDao(logger);
        return accountDao.findAll();
    }

    private void setParams(HttpServletRequest request, LinkedList<NewAccountRequest> newAccountRequests, LinkedList<Account> activeAccounts) {
        request.setAttribute("requests", newAccountRequests);
        request.setAttribute("active_accounts", activeAccounts);
    }

}
