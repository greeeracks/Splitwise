package com.saidul.Splitwise.Exception;

public class UserRegistrationInvalidDataException extends RuntimeException{
    public UserRegistrationInvalidDataException() {
        super();
    }

    public UserRegistrationInvalidDataException(String message) {
        super(message);
    }

    public UserRegistrationInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistrationInvalidDataException(Throwable cause) {
        super(cause);
    }

    protected UserRegistrationInvalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
