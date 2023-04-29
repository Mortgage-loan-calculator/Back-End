package com.it.academy.mortgage.calculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailSenderService emailSenderService;

    public void sendEmail(String toEmail) {
        String subject = "Application for mortgage at Team SwEB";
        String body = "We got your information and will get back to you shortly!!! \n";

        emailSenderService.sendEmail(toEmail, subject, body);
    }
}
