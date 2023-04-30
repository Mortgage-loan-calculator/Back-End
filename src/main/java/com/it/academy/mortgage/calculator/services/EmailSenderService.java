package com.it.academy.mortgage.calculator.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailSenderService {

    @Autowired
    private SendGrid sendGrid;
    private final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);

    public void sendEmail(String toEmail) {
        Email from = new Email("mortgage.calculator.sweb@outlook.com");
        Email to = new Email(toEmail);
        Content emailContent = new Content("text/html", "We got your information and will get back to you shortly!!!");
        Mail mail = new Mail(from, "Application for mortgage at Team sWEB", to, emailContent);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
