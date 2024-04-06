package com.saidul.Splitwise.Exception;

public class InvalidFriendRequestException extends RuntimeException{
    public InvalidFriendRequestException() {
        super();
    }

    public InvalidFriendRequestException(String message) {
        super(message);
    }

    public InvalidFriendRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFriendRequestException(Throwable cause) {
        super(cause);
    }

    protected InvalidFriendRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
