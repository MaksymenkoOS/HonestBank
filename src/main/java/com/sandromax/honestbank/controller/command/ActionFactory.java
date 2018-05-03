package com.sandromax.honestbank.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sandro on 26.04.18.
 */
public class ActionFactory {
    public Command defineCommand(HttpServletRequest request) {
        Command command = null;
        String commandParam = request.getParameter("command");

        if(commandParam == null || commandParam.isEmpty())
            return command;

        try {
            CommandList currentEnum = CommandList.valueOf(commandParam.toUpperCase());
            command = currentEnum.getCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrong_command", commandParam);
        }

        return command;
    }
}
