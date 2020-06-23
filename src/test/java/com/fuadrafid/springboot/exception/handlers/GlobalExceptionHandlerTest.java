package com.fuadrafid.springboot.exception.handlers;

import com.fuadrafid.springboot.exception.ApplicationInternalException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    @Test
    void test_handleException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        final var result = globalExceptionHandler.handleApplicationInternalException(new ApplicationInternalException("some message",
                HttpStatus.BAD_REQUEST));
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(result.getBody()).getMessage()).isEqualTo("some message");
    }


}