package com.saidul.Splitwise.Exception;

public class InvalidGroupRequestData extends RuntimeException{
    public InvalidGroupRequestData() {
        super();
    }

    public InvalidGroupRequestData(String message) {
        super(message);
    }

    public InvalidGroupRequestData(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGroupRequestData(Throwable cause) {
        super(cause);
    }

    protected InvalidGroupRequestData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
