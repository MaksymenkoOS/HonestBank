package com.sandromax.honestbank.controller.command.impl;

import com.sandromax.honestbank.controller.command.Command;
import com.sandromax.honestbank.controller.until.constants.Pages;
import com.sandromax.honestbank.domain.bank.Operator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandReplenishment implements Command {
    int accountId;
    double sum;
    private static Logger logger = Logger.getLogger(CommandReplenishment.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.CHECK;

        if(collectParams(request)) {
            if(operation()) {
                setParams(request);
                page = Pages.CHECK;
            } else {
                page = Pages.DEPOSIT_ACCOUNT;
            }
        }
        System.out.println("page: " + page);

        return page;
    }

    private boolean collectParams(HttpServletRequest request) {
        boolean result = false;

        String accountIdParam = request.getParameter("account_id");
        System.out.println("accountIdParam: " + accountIdParam);
        String sumParam = request.getParameter("sum");
        System.out.println("sumParam: " + sumParam);

        try {
            accountId = Integer.parseInt(accountIdParam);
            sum = Double.parseDouble(sumParam);

            System.out.println("accountId: " + accountId);
            System.out.println("sum: " + sumParam);

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
        boolean result = operator.pay(accountId, sum);
        System.out.println("result(from CommandReplenishment.operation()): " + result);

        return result;
    }

    private void setParams(HttpServletRequest request) {
        request.setAttribute("account_id", accountId);
        request.setAttribute("sum", sum);
    }
}
