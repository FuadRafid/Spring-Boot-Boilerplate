package com.fuadrafid.springboot.dto.request;

import javax.validation.constraints.NotBlank;

public class MathRequestDto {
    @NotBlank(message = "Input cannot be blank or null")
    private String firstNumber;

    @NotBlank(message = "Input cannot be blank or null")
    private String secondNumber;


    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }
}
