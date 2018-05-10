package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.NewAccountRequestDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandNewAccount implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.USER_CABINET;

        HttpSession session = request.getSession();

        String accountTypeParam = (String)session.getAttribute("account_type");
        User user = (User)session.getAttribute("user");

        FileLogger logger = new FileLogger();
        if(accountTypeParam == null
                && accountTypeParam == ""
                && user == null) {
            logger.log("Sorry? Can't create new account");
            request.setAttribute("message", "Sorry? Can't create new account");
        } else {
            AccountType accountType = AccountType.valueOf(accountTypeParam.toUpperCase());
            System.out.println(accountTypeParam);
            NewAccountRequestDao dao = new NewAccountRequestDao(logger);
            NewAccountRequest newAccountRequest = new NewAccountRequest(accountType, user);
            dao.create(newAccountRequest);
        }

        //  todo Ask: why don't work code below?
        System.out.println(request.getAttribute("account_type"));
        System.out.println(request.getParameter("account_type"));

        return page;
    }
}
