package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.service.BCrypt;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandUserSignIn implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.USER_CABINET;

        sessionLogOut(request);

        String emailParam = request.getParameter("email");
        String passParam = request.getParameter("password");

        HttpSession session = request.getSession();

        UserDao userDao = new UserDao(new FileLogger());
        User user = userDao.findByEmail(emailParam);

        if(user == null) {
            page = Pages.INDEX;
            return page;
        }
        else {
            String passDb = user.getPass();
            if(passDb.length() != 0 && (BCrypt.checkpw(passParam, passDb))) {
//            session.setAttribute("user_name", user.getName());
                user.clearPass();
                session.setAttribute("user", user);
            } else {
                page = Pages.INDEX;
                request.setAttribute("error_message", "wrong email or password");
            }
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
