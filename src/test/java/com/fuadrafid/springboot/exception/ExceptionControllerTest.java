package com.fuadrafid.springboot.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ExceptionControllerTest {
    @InjectMocks
    ExceptionController exceptionController;

    @Mock
    ExceptionResponseFactory mockExceptionResponseFactory;

    @Test
    void test_handleException() {
        Map<String, Object> data = new HashMap<>();
        data.put("someKey", "someData");
        final ResponseEntity<Map<String, Object>> expectedResult = new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        when(mockExceptionResponseFactory.handleException(any())).thenReturn(expectedResult);

        final var result = exceptionController.handleException(new ServiceException("some message",
                ExceptionCode.DEFAULT, null));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void test_handleMethodArgumentNotValid_shouldReturnCorrectResponseBody() {
        final MethodArgumentNotValidException e = mock(MethodArgumentNotValidException.class, RETURNS_DEEP_STUBS);

        final List<FieldError> errors = new ArrayList<>();
        errors.add(new FieldError("some object", "someField", "one error"));
        errors.add(new FieldError("some object", "someField", "two error"));

        when(e.getBindingResult().getFieldErrors()).thenReturn(errors);

        final var result = exceptionController.handleMethodArgumentNotValid(e, null, HttpStatus.OK, null);
        final var responseBody = (HashMap<String, Object>) result.getBody();
        assertThat(responseBody.get("messages")).isEqualTo(errors
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));
        assertThat(responseBody.get("status")).isEqualTo(HttpStatus.OK.value());
        assertThat(responseBody.get("error")).isEqualTo("Invalid parameter error");
    }
}