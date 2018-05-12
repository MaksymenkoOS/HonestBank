package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.service.BCrypt;
import com.sandromax.honestbank.domain.service.log.FileLogger;
import com.sandromax.honestbank.domain.user.Admin;
import com.sandromax.honestbank.model.dao.impl.AdminDao;
import com.sandromax.honestbank.model.dao.impl.NewAccountRequestDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

public class CommandAdminSignIn implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;

        if(authorizeAdmin(request)) {
            page = Pages.ADMIN_CABINET;

            LinkedList<NewAccountRequest> requests = getAllNotConfirmedRequests();
            request.setAttribute("requests", requests);
        }
        else {
            page = Pages.ADMIN_SIGN_IN;

        }

        return page;
    }

    private boolean authorizeAdmin(HttpServletRequest request) {
        sessionLogOut(request);

        String emailParam = request.getParameter("email");
        String passParam = request.getParameter("password");

        HttpSession session = request.getSession();

        AdminDao adminDao = new AdminDao(new FileLogger());
        Admin admin = adminDao.findByEmail(emailParam);

        if(admin == null) {
            return false;
        }
        else {
            String passDb = admin.getPass();
            if(passDb.length() != 0 && (BCrypt.checkpw(passParam, passDb))) {
//            session.setAttribute("user_name", user.getName());
                admin.clearPass();
                session.setAttribute("admin", admin);

                return true;
            } else {
                request.setAttribute("error_message", "wrong email or password");
                return false;
            }
        }
    }

    private LinkedList<NewAccountRequest> getAllNotConfirmedRequests() {
        NewAccountRequestDao dao = new NewAccountRequestDao(new FileLogger());
        LinkedList<NewAccountRequest> requests = dao.findAllNotConfirmed();

        return requests;
    }

    private void sessionLogOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }
}
