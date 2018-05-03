package com.sandromax.honestbank.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sandro on 26.04.18.
 */
public interface Command {
    String execute(HttpServletRequest request);
}
