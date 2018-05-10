package com.sandromax.honestbank.model.exception;

public class DaoException extends Exception {
    @Override
    public String getMessage() {
        return "DaoException";
    }
}
