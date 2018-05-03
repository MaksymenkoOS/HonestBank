package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;

import javax.servlet.http.HttpServletRequest;

public class CommandIndex implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.INDEX;
        return page;
    }
}
