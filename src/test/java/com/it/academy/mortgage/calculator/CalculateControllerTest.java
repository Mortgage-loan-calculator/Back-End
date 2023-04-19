package com.it.academy.mortgage.calculator;

import com.it.academy.mortgage.calculator.exceptions.CalculatorException;
import com.it.academy.mortgage.calculator.exceptions.CalculatorExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateControllerTest {

    @Test
    public void testHandleEuriborException() {
        CalculatorException exception = new CalculatorException();
        CalculatorExceptionHandler calculatorExceptionHandler = new CalculatorExceptionHandler();
        ResponseEntity<String> response = calculatorExceptionHandler.handleEuriborException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error, could not get Euribor rates from Swedbank", response.getBody());
    }
}




