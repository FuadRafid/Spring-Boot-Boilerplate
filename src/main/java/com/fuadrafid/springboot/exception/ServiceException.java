package com.fuadrafid.springboot.exception;

public class ServiceException extends RuntimeException {
    private final ExceptionCode code;

    public ServiceException(final String message, final ExceptionCode code, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(final String message, final ExceptionCode code) {
        super(message);
        this.code = code;
    }

    public ExceptionCode getCode() {
        return code;
    }
}

