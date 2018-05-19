package com.sandromax.honestbank.model.exception;

public class DaoException extends Exception {
//    String message;
//    Throwable cause;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {

        return "DaoException";
    }
}
