package com.it.academy.mortgage.calculator;

import com.it.academy.mortgage.calculator.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmail() {
        emailService.sendEmail("juozas.marozas159@gmail.com");
    }
}
