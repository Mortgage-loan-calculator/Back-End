package com.it.academy.mortgage.calculator.security.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfiguration {

    @Value("${EMAIL_API_KEY}")
    private String apiKey;

    @Bean
    public SendGrid sendGrid() {
        return new SendGrid(apiKey);
    }
}
