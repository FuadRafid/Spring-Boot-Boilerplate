package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.request.MathRequestDto;
import com.fuadrafid.springboot.exception.ServiceException;
import com.fuadrafid.springboot.service.MathService;
import com.fuadrafid.springboot.service.impl.MathServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MathControllerTest {
    @InjectMocks
    MathController mathController = new MathController();

    @Spy
    MathService service = new MathServiceImpl();

    @Test
    void helloMessage_shouldReturnCorrect() {
        assertThat(Objects.requireNonNull(mathController.helloMessage().getBody()).getMessage())
                .isEqualTo("Hello, this is a calculator.");
    }

    @Test
    void addMessage_shouldReturnCorrectMessage() {
        assertThat(Objects.requireNonNull(mathController.addMessage().getBody()).getMessage())
                .isEqualTo("To add, use the url: /calculator/add/{firstNumber}/{secondNumber}.");
    }

    @Test
    void addNumbers_shouldReturnCorrectAnswer_givenValidNumbers() {
        assertThat(Objects.requireNonNull(mathController.addNumbers("3", "2").getBody()).getAnswer())
                .isEqualTo("5");
    }

    @Test
    void addNumbers_shouldThrowException_givenInvalidInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathController.addNumbers("abc", "23"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void divideNumbers_shouldReturnCorrectAnswer_givenValidNumbers() {
        MathRequestDto mathRequestDto = new MathRequestDto();
        mathRequestDto.setFirstNumber("6");
        mathRequestDto.setSecondNumber("2");
        assertThat(Objects.requireNonNull(mathController.divideNumbers(mathRequestDto).getBody()).getAnswer())
                .isEqualTo("3");
    }

    @Test
    void divideNumbers_shouldThrowException_givenInvalidInput() {
        MathRequestDto mathRequestDto = new MathRequestDto();
        mathRequestDto.setFirstNumber("abc");
        mathRequestDto.setSecondNumber("2");
        ServiceException exception = assertThrows(ServiceException.class, () -> mathController.divideNumbers(mathRequestDto));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void divideNumbers_shouldThrowException_givenDividedByZero() {
        MathRequestDto mathRequestDto = new MathRequestDto();
        mathRequestDto.setFirstNumber("3");
        mathRequestDto.setSecondNumber("0");
        ServiceException exception = assertThrows(ServiceException.class, () -> mathController.divideNumbers(mathRequestDto));
        assertThat(exception.getMessage()).isEqualTo("Cannot divide by 0");
    }

    @Test
    void subtractNumbers_shouldReturnCorrectAnswer_givenValidNumbers() {
        assertThat(Objects.requireNonNull(mathController.subtractNumbers("6", "2").getBody()).getAnswer())
                .isEqualTo("4");
    }

    @Test
    void subtractNumbers_shouldThrowException_givenInvalidInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathController.subtractNumbers("abc", "23"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");

    }

    @Test
    void multiplyNumber_shouldReturnCorrectAnswer_givenValidNumbers() {
        assertThat(Objects.requireNonNull(mathController.multiplyNumber("6", "2").getBody()).getAnswer())
                .isEqualTo("12");
    }

    @Test
    void multiplyNumber_shouldThrowException_givenInvalidInput() {
        ServiceException exception = assertThrows(ServiceException.class, () -> mathController.multiplyNumber("abc", "23"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");

    }
}