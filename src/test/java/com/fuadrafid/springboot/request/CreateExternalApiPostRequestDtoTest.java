package com.fuadrafid.springboot.request;

import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateExternalApiEmployeeRequestDtoTest {
    private Validator validator;

    @BeforeAll
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCalculationRequestDto_shouldBeInvalid_givenBlankStringAndZeroInputs() {
        CreateExternalApiEmployeeDto requestDto = new CreateExternalApiEmployeeDto();
        requestDto.setSalary(0);
        requestDto.setAge(0);
        requestDto.setName("");
        Set<ConstraintViolation<CreateExternalApiEmployeeDto>> violations = validator.validate(requestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<CreateExternalApiEmployeeDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(3);
        assertThat(messages.contains("name cannot be blank or null")).isTrue();
        assertThat(messages.contains("salary must be positive")).isTrue();
        assertThat(messages.contains("age must be positive")).isTrue();

    }

    @Test
    public void testCalculationRequestDto_shouldBeInvalid_givenNullStringAndNegativeInputs() {
        CreateExternalApiEmployeeDto requestDto = new CreateExternalApiEmployeeDto();
        requestDto.setSalary(-1);
        requestDto.setAge(-1);
        requestDto.setName(null);
        Set<ConstraintViolation<CreateExternalApiEmployeeDto>> violations = validator.validate(requestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<CreateExternalApiEmployeeDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(3);
        assertThat(messages.contains("name cannot be blank or null")).isTrue();
        assertThat(messages.contains("salary must be positive")).isTrue();
        assertThat(messages.contains("age must be positive")).isTrue();
    }

    @Test
    public void testCalculationRequestDto_shouldBeValid_givenValidnputs() {
        CreateExternalApiEmployeeDto requestDto = new CreateExternalApiEmployeeDto();
        requestDto.setSalary(1);
        requestDto.setAge(111);
        requestDto.setName("name");
        Set<ConstraintViolation<CreateExternalApiEmployeeDto>> violations = validator.validate(requestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<CreateExternalApiEmployeeDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isTrue();
    }

}