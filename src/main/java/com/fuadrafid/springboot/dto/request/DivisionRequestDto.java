package com.fuadrafid.springboot.dto.request;

import javax.validation.constraints.NotBlank;

public class DivisionRequestDto {
    @NotBlank(message = "Input cannot be blank or null")
    private String dividend;

    @NotBlank(message = "Input cannot be blank or null")
    private String divisor;


    public String getDividend() {
        return dividend;
    }

    public void setDividend(String firstNumber) {
        this.dividend = firstNumber;
    }

    public String getDivisor() {
        return divisor;
    }

    public void setDivisor(String divisor) {
        this.divisor = divisor;
    }
}
