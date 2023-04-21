package com.it.academy.mortgage.calculator;

import com.it.academy.mortgage.calculator.services.CalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Mock
    private CalculatorService calculatorService;

    @Test
    void contextLoads() {
        org.assertj.core.api.Assertions.assertThat(calculatorService).isNotNull();
    }

}
