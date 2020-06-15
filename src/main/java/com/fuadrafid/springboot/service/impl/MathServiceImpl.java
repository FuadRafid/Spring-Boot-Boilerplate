package com.fuadrafid.springboot.service.impl;


import com.fuadrafid.springboot.exception.ExceptionCode;
import com.fuadrafid.springboot.exception.ServiceException;
import com.fuadrafid.springboot.service.MathService;
import org.springframework.stereotype.Service;

@Service(value = "defaultMathService")
public class MathServiceImpl implements MathService {

    private static final String WRONG_FORMAT_MESSAGE = "Wrong number format, inputs must be integers";
    @Override
    public String divideNumbers(String firstNumber, String secondNumber) {
        try {
            int firstIntNumber = Integer.parseInt(firstNumber);
            int secondIntNumber = Integer.parseInt(secondNumber);
            return Integer.toString(firstIntNumber / secondIntNumber);
        } catch (NumberFormatException e) {
            throw new ServiceException(WRONG_FORMAT_MESSAGE, ExceptionCode.WRONG_NUMBER_FORMAT, e);
        } catch (ArithmeticException e) {
            throw new ServiceException("Cannot divide by 0", ExceptionCode.DIVIDE_BY_ZERO, e);

        }

    }

    @Override
    public String multiplyNumbers(String firstNumber, String secondNumber) {
        try {
            int firstIntNumber = Integer.parseInt(firstNumber);
            int secondIntNumber = Integer.parseInt(secondNumber);
            return Integer.toString(firstIntNumber * secondIntNumber);
        } catch (NumberFormatException e) {
            throw new ServiceException(WRONG_FORMAT_MESSAGE, ExceptionCode.WRONG_NUMBER_FORMAT, e);
        }
    }

    @Override
    public String addNumbers(String firstNumber, String secondNumber) {
        try {
            int firstIntNumber = Integer.parseInt(firstNumber);
            int secondIntNumber = Integer.parseInt(secondNumber);
            return Integer.toString(firstIntNumber + secondIntNumber);
        } catch (NumberFormatException e) {
            throw new ServiceException(WRONG_FORMAT_MESSAGE, ExceptionCode.WRONG_NUMBER_FORMAT, e);
        }


    }

    @Override
    public String subtractNumbers(String firstNumber, String secondNumber) {
        try {
            int firstIntNumber = Integer.parseInt(firstNumber);
            int secondIntNumber = Integer.parseInt(secondNumber);
            return Integer.toString(firstIntNumber - secondIntNumber);
        } catch (NumberFormatException e) {
            throw new ServiceException(WRONG_FORMAT_MESSAGE, ExceptionCode.WRONG_NUMBER_FORMAT, e);
        }
    }
}
