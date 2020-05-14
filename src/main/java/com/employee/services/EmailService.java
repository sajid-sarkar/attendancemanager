package com.employee.services;

import com.employee.entity.User;

public interface EmailService {

    void sendUpdatePasswordEmail(User user);

}
