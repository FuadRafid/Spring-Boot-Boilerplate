package com.fuadrafid.springboot.service;


import com.fuadrafid.springboot.dto.response.MathResponseDto;

public interface MathService {
    public MathResponseDto divideNumbers(String firstNumber, String secondNumber);

    public MathResponseDto multiplyNumbers(String firstNumber, String secondNumber);

    public MathResponseDto addNumbers(String firstNumber, String secondNumber);

    public MathResponseDto subtractNumbers(String firstNumber, String secondNumber);
}
