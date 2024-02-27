package com.fuadrafid.springboot.exception.handlers;

import com.fuadrafid.springboot.exception.response.ValidationExceptionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SomeClass{
    public int someMethod(int param){
        return param;
    }
}
class ValidationExceptionHandlerTest {

    @Test
    void test_handleMethodArgumentNotValid_shouldReturnCorrectResponseBody() throws NoSuchMethodException {
        ValidationExceptionHandler exceptionHandler = new ValidationExceptionHandler();
        final List<FieldError> errors = new ArrayList<>();
        errors.add(new FieldError("some object", "someField", "one error"));
        errors.add(new FieldError("some object", "someField", "two error"));
        SomeClass someClass= new SomeClass();
        MethodParameter methodParameter = new MethodParameter(someClass.getClass().getMethod("someMethod", int.class), 0);
        BindingResult bindingResult = new BeanPropertyBindingResult(someClass,"SomeClassObject" );
        BindingResult bindingResultSpy = spy(bindingResult);

        when(bindingResultSpy.getFieldErrors()).thenReturn(errors);
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(methodParameter, bindingResultSpy);


        WebRequest mockWebRequest = new ServletWebRequest(new MockHttpServletRequest());
        final var result = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, mockWebRequest);
        final var responseBody = (ValidationExceptionResponse) result.getBody();
        assertThat(Objects.requireNonNull(responseBody).getMessages().get(0)).isEqualTo("one error");
        assertThat(Objects.requireNonNull(responseBody).getMessages().get(1)).isEqualTo("two error");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}