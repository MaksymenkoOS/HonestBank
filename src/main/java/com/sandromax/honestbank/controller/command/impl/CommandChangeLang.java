package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandChangeLang implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String lang = request.getParameter("lang");
        System.out.println(lang);

        session.setAttribute("lang", lang);

        return (String) session.getAttribute("current_page");
    }
}
