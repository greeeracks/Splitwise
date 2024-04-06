package com.saidul.Splitwise.Exception;

public class InvalidFriendRequestData extends RuntimeException{
    public InvalidFriendRequestData() {
        super();
    }

    public InvalidFriendRequestData(String message) {
        super(message);
    }

    public InvalidFriendRequestData(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFriendRequestData(Throwable cause) {
        super(cause);
    }

    protected InvalidFriendRequestData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
