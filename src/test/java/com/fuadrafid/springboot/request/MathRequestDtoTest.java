package com.fuadrafid.springboot.request;

import com.fuadrafid.springboot.dto.request.MathRequestDto;
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
public class MathRequestDtoTest {
    private Validator validator;

    @BeforeAll
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCalculationRequestDto_shouldBeInvalid_givenBlankStringInputs() {
        MathRequestDto mathRequestDto = new MathRequestDto();
        mathRequestDto.setFirstNumber("");
        mathRequestDto.setSecondNumber("");

        Set<ConstraintViolation<MathRequestDto>> violations = validator.validate(mathRequestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<MathRequestDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(2);
        assertThat(messages.get(0)).isEqualTo("Input cannot be blank or null");
        assertThat(messages.get(1)).isEqualTo("Input cannot be blank or null");
    }

    @Test
    public void test_NotNumberValidator_shouldBeInvalid_givenNullInputs() {
        MathRequestDto mathRequestDto = new MathRequestDto();
        mathRequestDto.setFirstNumber(null);
        mathRequestDto.setSecondNumber(null);

        Set<ConstraintViolation<MathRequestDto>> violations = validator.validate(mathRequestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<MathRequestDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(2);
        assertThat(messages.get(0)).isEqualTo("Input cannot be blank or null");
        assertThat(messages.get(1)).isEqualTo("Input cannot be blank or null");
    }

    @Test
    public void test_NotNumberValidator_shouldBeValid_givenNonBlankStringInputs() {
        MathRequestDto mathRequestDto = new MathRequestDto();
        mathRequestDto.setFirstNumber("3");
        mathRequestDto.setSecondNumber("abc");

        Set<ConstraintViolation<MathRequestDto>> violations = validator.validate(mathRequestDto);
        ArrayList<String> messages = new ArrayList<>();

        for (ConstraintViolation<MathRequestDto> violation : violations) {
            messages.add(violation.getMessage());
        }

        assertThat(violations.isEmpty()).isTrue();
    }
}
