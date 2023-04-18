package com.it.academy.mortgage.calculator.exceptions;

public class CalculatorException extends RuntimeException {
    public CalculatorException() {
        super("Error, could not get Euribor rates from Swedbank");
    }
}
