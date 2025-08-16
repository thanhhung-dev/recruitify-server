package com.recruitify.server.Util.Error;

public class IdInvalidException extends Exception{
    public IdInvalidException() {
    }

    public IdInvalidException(String message) {
        super(message);
    }

    public IdInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdInvalidException(Throwable cause) {
        super(cause);
    }

    public IdInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
