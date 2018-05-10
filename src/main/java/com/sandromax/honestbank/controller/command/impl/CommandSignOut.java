package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandSignOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.INDEX;

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        return page;
    }
}
