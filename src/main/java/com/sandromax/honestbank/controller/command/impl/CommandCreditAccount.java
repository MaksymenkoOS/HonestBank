package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandCreditAccount implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.CREDIT_ACCOUNT;

        HttpSession session = request.getSession();
        session.setAttribute("account_type", "credit");

        return page;
    }
}
