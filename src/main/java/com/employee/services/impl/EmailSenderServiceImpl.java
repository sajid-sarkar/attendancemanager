package com.employee.services.impl;

import com.employee.services.EmailSenderService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Value("${sendgrid.api-key}")
    private String senGridKey;

    @Override
    public void sendMail(String to, String subject, String contentBody) {
        Email from = new Email("sajid.sarkar@jellyfishtechnologies.com", "Attendance Manager Team");
        Email toUser = new Email(to);
        Content content = new Content("text/html", contentBody);
        Mail mail = new Mail(from, subject, toUser, content);
        SendGrid sendGrid = new SendGrid(senGridKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());
            System.out.println("Response header: " + response.getHeaders());
        } catch (IOException e) {
            System.out.println("mailIOException");
            e.printStackTrace();
        }
    }
}
