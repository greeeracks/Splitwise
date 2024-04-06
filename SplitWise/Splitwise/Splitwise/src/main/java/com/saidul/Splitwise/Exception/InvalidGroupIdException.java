package com.saidul.Splitwise.Exception;

public class InvalidGroupIdException extends RuntimeException{
    public InvalidGroupIdException() {
        super();
    }

    public InvalidGroupIdException(String message) {
        super(message);
    }

    public InvalidGroupIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGroupIdException(Throwable cause) {
        super(cause);
    }

    protected InvalidGroupIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
