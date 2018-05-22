package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.account.Transaction;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.model.dao.impl.AccountDao;
import com.sandromax.honestbank.model.dao.impl.NewAccountRequestDao;
import com.sandromax.honestbank.model.dao.impl.TransactionDao;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

public class CommandDeclineRequest implements Command {
    Logger logger = new FileLogger();
    int requestId;

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.ADMIN_CABINET;

        collectParams(request);

        declineRequest(requestId);

        setParams(request, createNewRequestList(), getAllActiveAccounts(), getAllTransactions());

        return page;
    }

    private void declineRequest(int requestId) {
        NewAccountRequestDao dao = new NewAccountRequestDao();
        dao.setDeclinedById(requestId);
    }

    private void collectParams(HttpServletRequest request) {
        try{
            requestId = Integer.parseInt(request.getParameter("request_id"));
        } catch (NullPointerException e) {
            logger.log(e.getMessage());
        } catch (Exception e) {
            logger.log(e.getMessage());
        }

    }

    private LinkedList<NewAccountRequest> createNewRequestList() {
        NewAccountRequestDao dao = new NewAccountRequestDao();
        return dao.findAllNotConfirmed();
    }

    private LinkedList<Account> getAllActiveAccounts() {
        AccountDao accountDao = new AccountDao();
        return accountDao.findAll();
    }

    private LinkedList<Transaction> getAllTransactions() {
        TransactionDao transactionDao = new TransactionDao();
        return transactionDao.findAll();
    }

    private void setParams(HttpServletRequest request, LinkedList<NewAccountRequest> newAccountRequests, LinkedList<Account> activeAccounts, LinkedList<Transaction> transactions) {
        request.setAttribute("requests", newAccountRequests);
        request.setAttribute("active_accounts", activeAccounts);
        request.setAttribute("all_transactions", transactions);
    }
}
