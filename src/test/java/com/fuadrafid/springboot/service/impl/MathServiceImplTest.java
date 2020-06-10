package com.fuadrafid.springboot.service.impl;

import com.fuadrafid.springboot.exception.ServiceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MathServiceImplTest {

    MathServiceImpl mathService = new MathServiceImpl();

    @Test
    void addNumbers_shouldReturnCorrectAnswer() {
        assertThat(mathService.addNumbers("3", "2").getAnswer()).isEqualTo("5");
    }

    @Test
    void divideNumbers_shouldReturnCorrectAnswer() {
        assertThat(mathService.divideNumbers("6", "2").getAnswer()).isEqualTo("3");
    }

    @Test
    void subtractNumbers_shouldReturnCorrectAnswer() {
        assertThat(mathService.subtractNumbers("3", "2").getAnswer()).isEqualTo("1");
    }

    @Test
    void multiplyNumbers_shouldReturnCorrectAnswer() {
        assertThat(mathService.multiplyNumbers("3", "2").getAnswer()).isEqualTo("6");
    }


    @Test
    void addNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathService.addNumbers("a", "2"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void divideNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathService.divideNumbers("3", "c"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void subtractNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathService.subtractNumbers("3", "&"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void multiplyNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathService.multiplyNumbers("3", "."));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void divideNumbers_shouldThrowException_ifDividedByZero() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathService.divideNumbers("3", "0"));
        assertThat(exception.getMessage()).isEqualTo("Cannot divide by 0");
    }


}