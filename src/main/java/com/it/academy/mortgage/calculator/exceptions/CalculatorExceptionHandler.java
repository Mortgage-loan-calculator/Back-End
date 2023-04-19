package com.it.academy.mortgage.calculator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CalculatorExceptionHandler {

    @ExceptionHandler(CalculatorException.class)
    public ResponseEntity<String> handleEuriborException(CalculatorException calculatorException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(calculatorException.getMessage());
    }
}
