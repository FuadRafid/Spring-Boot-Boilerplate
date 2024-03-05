package com.fuadrafid.springboot.services;


public interface MathService {
    String divideNumbers(String firstNumber, String secondNumber);

    String multiplyNumbers(String firstNumber, String secondNumber);

    String getPower(String number, String power);

    String getExponential(String input);
}
