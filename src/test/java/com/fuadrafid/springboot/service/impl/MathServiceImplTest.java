package com.fuadrafid.springboot.service.impl;

import com.fuadrafid.springboot.exception.ServiceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MathServiceImplTest {

    MathServiceImpl messageService = new MathServiceImpl();

    @Test
    public void addNumbers_shouldReturnCorrectAnswer() {
        assertThat(messageService.addNumbers("3", "2").getAnswer()).isEqualTo("5");
    }

    @Test
    public void divideNumbers_shouldReturnCorrectAnswer() {
        assertThat(messageService.divideNumbers("6", "2").getAnswer()).isEqualTo("3");
    }

    @Test
    public void subtractNumbers_shouldReturnCorrectAnswer() {
        assertThat(messageService.subtractNumbers("3", "2").getAnswer()).isEqualTo("1");
    }

    @Test
    public void multiplyNumbers_shouldReturnCorrectAnswer() {
        assertThat(messageService.multiplyNumbers("3", "2").getAnswer()).isEqualTo("6");
    }


    @Test
    public void addNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> messageService.addNumbers("a", "2").getAnswer());
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    public void divideNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> messageService.divideNumbers("3", "c").getAnswer());
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    public void subtractNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> messageService.subtractNumbers("3", "&").getAnswer());
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    public void multiplyNumbers_shouldThrowException_givenNonNumberInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> messageService.multiplyNumbers("3", ".").getAnswer());
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    public void divideNumbers_shouldThrowException_ifDividedByZero() {
        ServiceException exception = assertThrows(ServiceException.class, () -> messageService.divideNumbers("3", "0").getAnswer());
        assertThat(exception.getMessage()).isEqualTo("Cannot divide by 0");
    }


}