package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.service.BCrypt;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.user.Admin;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.AdminDao;
import com.sandromax.honestbank.model.dao.impl.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CommandUserSignUp implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        //todo Ask: where I should to put logger instance
        String page = Pages.USER_CABINET;

        String nameParam = request.getParameter("name");
        String emailParam = request.getParameter("email");
        String passParam = request.getParameter("pass");
        String passHash = BCrypt.hashpw(passParam, BCrypt.gensalt(13));

        UserDao userDao = new UserDao();
        User user = new User(nameParam, emailParam, passHash);

        if(userDao.findByEmail(emailParam) == null) {
            userDao.create(user);

            sessionLogOut(request);
            HttpSession session = request.getSession();

            user.clearPass();
            session.setAttribute("user", user);
        }
        else {
            page = Pages.USER_SIGN_UP;
            String errorMessage = "email has already been registered";
            request.setAttribute("message", errorMessage);
        }

        return page;
    }

    private void sessionLogOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }

}
