package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.bank.Operator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandTransfer implements Command {

    int accountIdFrom;
    int accountIdTo;
    double sum;
    private static Logger logger = Logger.getLogger(CommandTransfer.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.CHECK;

        if(collectParams(request)) {
            if(operation()) {
                setParams(request);
                page = Pages.CHECK;
            } else {
                page = Pages.USER_CABINET;
                request.setAttribute("message", "Error! Transfer was not successful.");
            }
        }
        System.out.println("page: " + page);

        return page;
    }

    private boolean collectParams(HttpServletRequest request) {
        boolean result = false;

        String accountIdFromParam = request.getParameter("account_id");
        String accountIdToParam = request.getParameter("recipient");
        String sumParam = request.getParameter("sum");

        try{
            accountIdFrom = Integer.parseInt(accountIdFromParam);
            accountIdTo = Integer.parseInt(accountIdToParam);
            sum = Double.parseDouble(sumParam);

            result = true;
        } catch (NullPointerException nullEx) {
            logger.error(nullEx.getMessage());
        } catch (NumberFormatException numbEx) {
            logger.error(numbEx.getMessage());
        }

        return result;
    }

    private boolean operation() {
        Operator operator = Operator.getInstance();
        boolean result = operator.pay(accountIdFrom, accountIdTo, sum);

        return result;
    }

    private void setParams(HttpServletRequest request) {
        request.setAttribute("message", "Transfer from account: " + accountIdFrom + " to account: " + accountIdTo + " was successful. Transfer amount: " + sum + ".");


    }
}
