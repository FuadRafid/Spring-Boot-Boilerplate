package com.fuadrafid.springboot.request;

import com.fuadrafid.springboot.dto.request.employee.CreateEmployeeDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateEmployeeRequestDtoTest {
    private Validator validator;

    @BeforeAll
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void test_CreateEmployeeRequestDtoTest_shouldBeInvalid_givenBlankStringAndZeroInputs() {
        CreateEmployeeDto requestDto = new CreateEmployeeDto();
        requestDto.setSalary(0);
        requestDto.setAge(0);
        requestDto.setName("");
        Set<ConstraintViolation<CreateEmployeeDto>> violations = validator.validate(requestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<CreateEmployeeDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(3);
        assertThat(messages.contains("name cannot be blank or null")).isTrue();
        assertThat(messages.contains("salary must be positive")).isTrue();
        assertThat(messages.contains("age must be positive")).isTrue();

    }

    @Test
    void test_CreateEmployeeRequestDtoTest_shouldBeInvalid_givenNullStringAndNegativeInputs() {
        CreateEmployeeDto requestDto = new CreateEmployeeDto();
        requestDto.setSalary(-1);
        requestDto.setAge(-1);
        requestDto.setName(null);
        Set<ConstraintViolation<CreateEmployeeDto>> violations = validator.validate(requestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<CreateEmployeeDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(3);
        assertThat(messages.contains("name cannot be blank or null")).isTrue();
        assertThat(messages.contains("salary must be positive")).isTrue();
        assertThat(messages.contains("age must be positive")).isTrue();
    }

    @Test
    void test_CreateEmployeeRequestDtoTest_shouldBeValid_givenValidnputs() {
        CreateEmployeeDto requestDto = new CreateEmployeeDto();
        requestDto.setSalary(1);
        requestDto.setAge(111);
        requestDto.setName("name");
        Set<ConstraintViolation<CreateEmployeeDto>> violations = validator.validate(requestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<CreateEmployeeDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isTrue();
    }

}