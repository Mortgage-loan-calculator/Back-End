package com.it.academy.mortgage.calculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailSenderService emailSenderService;

    public void sendEmail(String toEmail) {
        emailSenderService.sendEmail(toEmail);
    }
}
