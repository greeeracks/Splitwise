package com.saidul.Splitwise.Exception;

public class InvalidExpenseIdException extends RuntimeException{
    public InvalidExpenseIdException() {
        super();
    }

    public InvalidExpenseIdException(String message) {
        super(message);
    }

    public InvalidExpenseIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidExpenseIdException(Throwable cause) {
        super(cause);
    }

    protected InvalidExpenseIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
