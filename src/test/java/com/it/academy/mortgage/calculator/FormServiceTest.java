package com.it.academy.mortgage.calculator;

import com.it.academy.mortgage.calculator.services.FormsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FormServiceTest {


    @Test
    public void testFormatDecimal() {
        double value = 205.14;
        System.out.println(value);
        System.out.println("\n");
        value = Math.round(value);
        System.out.println(Math.round(value));
    }

}
