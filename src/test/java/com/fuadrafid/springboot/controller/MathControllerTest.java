package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.request.DivisionRequestDto;
import com.fuadrafid.springboot.exception.ApplicationInternalException;
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
    void divideNumbers_shouldReturnCorrectAnswer_givenValidNumbers() {
        DivisionRequestDto divisionRequestDto = new DivisionRequestDto();
        divisionRequestDto.setDividend("6");
        divisionRequestDto.setDivisor("2");
        assertThat(Objects.requireNonNull(mathController.divideNumbers(divisionRequestDto).getBody()).getAnswer())
                .isEqualTo("3");
    }

    @Test
    void divideNumbers_shouldThrowException_givenInvalidInput() {
        DivisionRequestDto divisionRequestDto = new DivisionRequestDto();
        divisionRequestDto.setDividend("abc");
        divisionRequestDto.setDivisor("2");
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathController.divideNumbers(divisionRequestDto));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");
    }

    @Test
    void divideNumbers_shouldThrowException_givenDividedByZero() {
        DivisionRequestDto divisionRequestDto = new DivisionRequestDto();
        divisionRequestDto.setDividend("3");
        divisionRequestDto.setDivisor("0");
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathController.divideNumbers(divisionRequestDto));
        assertThat(exception.getMessage()).isEqualTo("Cannot divide by 0");
    }

    @Test
    void power_shouldReturnCorrectAnswer_givenValidNumbers() {
        assertThat(Objects.requireNonNull(mathController.power("6", "2").getBody()).getAnswer())
                .isEqualTo("36.0");
    }

    @Test
    void power_shouldThrowException_givenInvalidInput() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathController.power("abc", "23"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be decimal numbers");

    }

    @Test
    void multiplyNumber_shouldReturnCorrectAnswer_givenValidNumbers() {
        assertThat(Objects.requireNonNull(mathController.multiplyNumber("6", "2").getBody()).getAnswer())
                .isEqualTo("12");
    }

    @Test
    void multiplyNumber_shouldThrowException_givenInvalidInput() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathController.multiplyNumber("abc", "23"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be integers");

    }

    @Test
    void exponential_shouldReturnCorrectAnswer_givenValidNumbers() {
        assertThat(Objects.requireNonNull(mathController.exponential("6.2").getBody()).getAnswer())
                .isEqualTo("492.7490410932563");
    }

    @Test
    void exponential_shouldThrowException_givenInvalidInput() {
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> mathController.exponential("abc"));
        assertThat(exception.getMessage()).isEqualTo("Wrong number format, inputs must be decimal numbers");

    }
}