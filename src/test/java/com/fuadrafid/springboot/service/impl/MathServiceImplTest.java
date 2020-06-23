package com.fuadrafid.springboot.service.impl;

import com.fuadrafid.springboot.exception.ApplicationInternalException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MathServiceImplTest {

    MathServiceImpl mathService = new MathServiceImpl();

    @Test
    void getPower_shouldReturnCorrectAnswer() {
        assertThat(mathService.getPower("3", "3")).isEqualTo("27.0");
    }

    @Test
    void divideNumbers_shouldReturnCorrectAnswer() {
        assertThat(mathService.divideNumbers("6", "2")).isEqualTo("3");
    }


    @Test
    void multiplyNumbers_shouldReturnCorrectAnswer() {
        assertThat(mathService.multiplyNumbers("3", "2")).isEqualTo("6");
    }

    @Test
    void getExponential_shouldReturnCorrectAnswer() {
        assertThat(mathService.getExponential("3")).isEqualTo("20.085536923187668");
    }


    @Test
    void divideNumbers_shouldThrowException_givenNonNumberInput() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathService.divideNumbers("3", "c"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void getPower_shouldThrowException_givenNonNumberInput() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathService.getPower("3", "&"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be decimal numbers");
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void multiplyNumbers_shouldThrowException_givenNonNumberInput() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathService.multiplyNumbers("3", "."));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void divideNumbers_shouldThrowException_ifDividedByZero() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathService.divideNumbers("3", "0"));
        assertThat(exception.getMessage()).isEqualTo("Cannot divide by 0");
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getExponential_shouldThrowException_givenNonNumberInput() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathService.getExponential("abc"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be decimal numbers");
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}