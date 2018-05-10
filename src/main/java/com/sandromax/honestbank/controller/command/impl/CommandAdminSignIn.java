package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.service.BCrypt;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.user.Admin;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.impl.AdminDao;
import com.sandromax.honestbank.model.dao.impl.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandAdminSignIn implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.ADMIN_CABINET;

        sessionLogOut(request);

        String emailParam = request.getParameter("email");
        String passParam = request.getParameter("password");

        HttpSession session = request.getSession();

        AdminDao adminDao = new AdminDao(new FileLogger());
        Admin admin = adminDao.findByEmail(emailParam);

        if(admin == null) {
            page = Pages.INDEX;
            return page;
        }
        else {
            String passDb = admin.getPass();
            if(passDb.length() != 0 && (BCrypt.checkpw(passParam, passDb))) {
//            session.setAttribute("user_name", user.getName());
                admin.clearPass();
                session.setAttribute("admin", admin);
            } else {
                page = Pages.ADMIN_SIGN_IN;
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
