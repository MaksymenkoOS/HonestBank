package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;

import javax.servlet.http.HttpServletRequest;

public class CommandCreditAccount implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.CREDIT_ACCOUNT;

        return page;
    }
}
