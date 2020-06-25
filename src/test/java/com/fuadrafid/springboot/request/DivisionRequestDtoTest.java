package com.fuadrafid.springboot.request;

import com.fuadrafid.springboot.dto.request.math.DivisionRequestDto;
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
class DivisionRequestDtoTest {
    private Validator validator;

    @BeforeAll
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void test_DivisionRequestDtoTest_shouldBeInvalid_givenBlankStringInputs() {
        DivisionRequestDto divisionRequestDto = new DivisionRequestDto();
        divisionRequestDto.setDividend("");
        divisionRequestDto.setDivisor("");

        Set<ConstraintViolation<DivisionRequestDto>> violations = validator.validate(divisionRequestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<DivisionRequestDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(2);
        assertThat(messages.get(0)).isEqualTo("Input cannot be blank or null");
        assertThat(messages.get(1)).isEqualTo("Input cannot be blank or null");
    }

    @Test
    void test_DivisionRequestDtoTest_shouldBeInvalid_givenNullInputs() {
        DivisionRequestDto divisionRequestDto = new DivisionRequestDto();
        divisionRequestDto.setDividend(null);
        divisionRequestDto.setDivisor(null);

        Set<ConstraintViolation<DivisionRequestDto>> violations = validator.validate(divisionRequestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<DivisionRequestDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(2);
        assertThat(messages.get(0)).isEqualTo("Input cannot be blank or null");
        assertThat(messages.get(1)).isEqualTo("Input cannot be blank or null");
    }

    @Test
    void test_DivisionRequestDtoTest_shouldBeValid_givenNonBlankStringInputs() {
        DivisionRequestDto divisionRequestDto = new DivisionRequestDto();
        divisionRequestDto.setDividend("3");
        divisionRequestDto.setDivisor("abc");

        Set<ConstraintViolation<DivisionRequestDto>> violations = validator.validate(divisionRequestDto);


        assertThat(violations.isEmpty()).isTrue();
    }
}
