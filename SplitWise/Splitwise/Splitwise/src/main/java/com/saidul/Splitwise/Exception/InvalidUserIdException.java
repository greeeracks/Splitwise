package com.saidul.Splitwise.Exception;

public class InvalidUserIdException extends RuntimeException{
    public InvalidUserIdException() {
        super();
    }

    public InvalidUserIdException(String message) {
        super(message);
    }

    public InvalidUserIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserIdException(Throwable cause) {
        super(cause);
    }

    protected InvalidUserIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
