package com.fuadrafid.springboot.service;


public interface MathService {
    String divideNumbers(String firstNumber, String secondNumber);

    String multiplyNumbers(String firstNumber, String secondNumber);

    String getPower(String number, String power);

    String getExponential(String input);
}
