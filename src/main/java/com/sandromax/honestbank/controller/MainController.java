package com.sandromax.honestbank.controller;

import com.sandromax.honestbank.controller.command.ActionFactory;
import com.sandromax.honestbank.controller.command.Command;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sandro on 26.04.18.
 */

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class MainController extends HttpServlet {
    private String encoding;
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        encoding = config.getInitParameter("PARAMETER_ENCODING");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (encoding != null) {
            System.out.println("encoding is null. UTF-8 fixing ...");
            req.setCharacterEncoding(encoding);
        }

        String page = null;
        ActionFactory action = new ActionFactory();
        Command command = action.defineCommand(req);
        page = command.execute(req);

        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
