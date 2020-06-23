package com.fuadrafid.springboot.service.impl;


import com.fuadrafid.springboot.exception.ApplicationInternalException;
import com.fuadrafid.springboot.service.MathService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service(value = "defaultMathService")
public class MathServiceImpl implements MathService {

    private static final String WRONG_INTEGER_FORMAT_MESSAGE = "Wrong number format, inputs must be integers";
    private static final String WRONG_DOUBLE_FORMAT_MESSAGE = "Wrong number format, inputs must be decimal numbers";

    @Override
    public String divideNumbers(String firstNumber, String secondNumber) {
        try {
            int firstIntNumber = Integer.parseInt(firstNumber);
            int secondIntNumber = Integer.parseInt(secondNumber);
            return Integer.toString(firstIntNumber / secondIntNumber);
        } catch (NumberFormatException e) {
            throw new ApplicationInternalException(WRONG_INTEGER_FORMAT_MESSAGE, HttpStatus.BAD_REQUEST);
        } catch (ArithmeticException e) {
            throw new ApplicationInternalException("Cannot divide by 0", HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public String multiplyNumbers(String firstNumber, String secondNumber) {
        try {
            int firstIntNumber = Integer.parseInt(firstNumber);
            int secondIntNumber = Integer.parseInt(secondNumber);
            return Integer.toString(firstIntNumber * secondIntNumber);
        } catch (NumberFormatException e) {
            throw new ApplicationInternalException(WRONG_INTEGER_FORMAT_MESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String getPower(String number, String power) {
        try {
            double numberDouble = Double.parseDouble(number);
            double powerDouble = Double.parseDouble(power);
            return Double.toString(Math.pow(numberDouble, powerDouble));
        } catch (NumberFormatException e) {
            throw new ApplicationInternalException(WRONG_DOUBLE_FORMAT_MESSAGE, HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public String getExponential(String input) {
        try {
            return Double.toString(Math.exp(Double.parseDouble(input)));
        } catch (NumberFormatException e) {
            throw new ApplicationInternalException(WRONG_DOUBLE_FORMAT_MESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
