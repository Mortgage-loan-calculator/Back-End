package com.it.academy.mortgage.calculator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.academy.mortgage.calculator.exceptions.CalculatorException;
import com.it.academy.mortgage.calculator.exceptions.CalculatorExceptionHandler;
import com.it.academy.mortgage.calculator.services.EuriborFetcher;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Test
    public void testHandleEuriborException() {
        CalculatorException exception = new CalculatorException();
        CalculatorExceptionHandler calculatorExceptionHandler = new CalculatorExceptionHandler();
        ResponseEntity<String> response = calculatorExceptionHandler.handleEuriborException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error, could not get Euribor rates from Swedbank", response.getBody());
    }

    @Test
    public void getEuriborData() throws IOException {
        EuriborFetcher euriborFetcher = new EuriborFetcher();
        assertEquals(3.855,euriborFetcher.fetchEuribor());
    }
}




