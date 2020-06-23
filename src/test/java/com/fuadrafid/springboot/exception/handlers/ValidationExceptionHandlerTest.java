package com.fuadrafid.springboot.exception.handlers;

import com.fuadrafid.springboot.exception.response.ValidationExceptionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ValidationExceptionHandlerTest {

    @Test
    void test_handleMethodArgumentNotValid_shouldReturnCorrectResponseBody() {
        ValidationExceptionHandler exceptionHandler = new ValidationExceptionHandler();
        final MethodArgumentNotValidException e = mock(MethodArgumentNotValidException.class, RETURNS_DEEP_STUBS);

        final List<FieldError> errors = new ArrayList<>();
        errors.add(new FieldError("some object", "someField", "one error"));
        errors.add(new FieldError("some object", "someField", "two error"));

        when(e.getBindingResult().getFieldErrors()).thenReturn(errors);
        when(e.getMessage()).thenReturn("Validation error");

        WebRequest mockWebRequest = new ServletWebRequest(new MockHttpServletRequest());
        final var result = exceptionHandler.handleMethodArgumentNotValid(e, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST,mockWebRequest );
        final var responseBody = (ValidationExceptionResponse) result.getBody();
        assertThat(Objects.requireNonNull(responseBody).getMessages().get(0)).isEqualTo("one error");
        assertThat(Objects.requireNonNull(responseBody).getMessages().get(1)).isEqualTo("two error");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}