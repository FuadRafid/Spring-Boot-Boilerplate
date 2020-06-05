package com.fuadrafid.springboot.exception;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class ExceptionResponseFactoryTest {

    ExceptionResponseFactory exceptionResponseFactory;

    @Test
    void handleException_shouldReturnCorrectMessage_forDeafaultErrorCode() {
        exceptionResponseFactory = new ExceptionResponseFactory();
        var result = exceptionResponseFactory
                .handleException(new ServiceException("Some message", ExceptionCode.DEFAULT));

        final var responseBody = (HashMap<String, Object>) result.getBody();
        assertThat(responseBody.get("message")).isEqualTo("An error occurred");
    }

    @Test
    void handleException_shouldReturnCorrectMessage_forWrongNumberFormatErrorCode() {
        exceptionResponseFactory = new ExceptionResponseFactory();
        var result = exceptionResponseFactory
                .handleException(new ServiceException("Some message", ExceptionCode.WRONG_NUMBER_FORMAT));

        final var responseBody = (HashMap<String, Object>) result.getBody();
        assertThat(responseBody.get("message")).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void handleException_shouldReturnCorrectMessage_forDivideByZeroErrorCode() {
        exceptionResponseFactory = new ExceptionResponseFactory();
        var result = exceptionResponseFactory
                .handleException(new ServiceException("Some message", ExceptionCode.DIVIDE_BY_ZERO));

        final var responseBody = (HashMap<String, Object>) result.getBody();
        assertThat(responseBody.get("message")).isEqualTo("Cannot divide by 0");
    }
}