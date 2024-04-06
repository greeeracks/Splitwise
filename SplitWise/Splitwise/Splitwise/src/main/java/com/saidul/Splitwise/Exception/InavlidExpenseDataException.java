package com.saidul.Splitwise.Exception;

public class InavlidExpenseDataException extends RuntimeException{
    public InavlidExpenseDataException() {
        super();
    }

    public InavlidExpenseDataException(String message) {
        super(message);
    }

    public InavlidExpenseDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InavlidExpenseDataException(Throwable cause) {
        super(cause);
    }

    protected InavlidExpenseDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
