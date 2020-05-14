package com.employee.services;

public interface EmailSenderService {

    void sendMail(final String to, final String subject, final String contentBody);

}
