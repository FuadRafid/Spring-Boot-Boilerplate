package com.fuadrafid.springboot.exception;

import org.springframework.http.HttpStatus;

public class ApplicationInternalException extends RuntimeException {
    private final HttpStatus statusCode;

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public ApplicationInternalException(final String message, final HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}

