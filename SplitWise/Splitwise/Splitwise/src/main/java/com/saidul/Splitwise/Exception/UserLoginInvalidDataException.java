package com.saidul.Splitwise.Exception;

public class UserLoginInvalidDataException extends RuntimeException{
    public UserLoginInvalidDataException() {
        super();
    }

    public UserLoginInvalidDataException(String message) {
        super(message);
    }

    public UserLoginInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLoginInvalidDataException(Throwable cause) {
        super(cause);
    }

    protected UserLoginInvalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
