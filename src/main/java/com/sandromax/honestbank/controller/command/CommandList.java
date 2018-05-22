package com.sandromax.honestbank.controller.command;

import com.sandromax.honestbank.controller.command.impl.*;

/**
 * Created by sandro on 26.04.18.
 */
public enum CommandList {
    ADMIN_SIGN_IN(new CommandAdminSignIn()),
    ADMIN_SIGN_IN_PAGE(new CommandAdminSignInPage()),
    CREDIT_ACCOUNT(new CommandCreditAccount()),
    DEPOSIT_ACCOUNT(new CommandDepositAccount()),
    INDEX(new CommandIndex()),
    REPLENISHMENT(new CommandReplenishment()),
    REPLENISHMENT_PAGE(new CommandReplenishmentPage()),
    TRANSFER(new CommandTransfer()),
    TRANSFER_PAGE(new CommandTransferPage()),
    USER_SIGN_IN(new CommandUserSignIn()),
    USER_SIGN_UP_PAGE(new CommandUserSignUpPage()),
    USER_SIGN_UP(new CommandUserSignUp()),
    USER_CABINET_PAGE(new CommandUserCabinetPage()),
//    ADMIN_CABINET_PAGE(new CommandAdminCabinetPage()),
    SIGN_OUT(new CommandSignOut()),
    NEW_ACCOUNT_REQUEST(new CommandNewAccountRequest()),
    ACCEPT_REQUEST(new CommandAcceptRequest()),
    DECLINE_REQUEST(new CommandDeclineRequest()),
    CHANGE_LANG(new CommandChangeLang());

    private CommandList(Command command) {
        this.command = command;
    }
    private Command command;

    public Command getCommand() {
        return command;
    }
}
