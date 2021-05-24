package com.target.camelvmmsync.exception;

public abstract class BaseException extends RuntimeException {
    public BaseException(String errorMessage) {
        super(errorMessage);
    }

    public BaseException(String errorMessage, Throwable exception) {
        super(errorMessage, exception);
    }
}
