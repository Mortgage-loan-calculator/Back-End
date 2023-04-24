package com.it.academy.mortgage.calculator;

import com.it.academy.mortgage.calculator.services.EuriborFetcher;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateControllerTest {

    @Test
    public void getEuriborData() throws IOException {
        EuriborFetcher euriborFetcher = new EuriborFetcher();
        assertEquals(3.855,euriborFetcher.fetchEuribor());
    }
}




