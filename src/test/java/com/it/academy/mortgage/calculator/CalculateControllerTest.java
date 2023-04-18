package com.it.academy.mortgage.calculator;

import com.it.academy.mortgage.calculator.controllers.CalculatorController;
import com.it.academy.mortgage.calculator.exceptions.CalculatorException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculateControllerTest {

    @Test
    public void testHandleEuriborException() {
        CalculatorException exception = new CalculatorException();
        CalculatorController calculatorController = new CalculatorController();
        ResponseEntity<String> response = calculatorController.handleEuriborException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error, could not get Euribor rates from Swedbank", response.getBody());
    }
}




