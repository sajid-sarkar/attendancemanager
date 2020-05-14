package com.employee.services.impl;

import com.employee.entity.User;
import com.employee.services.EmailSenderService;
import com.employee.services.EmailService;
import com.employee.utils.EmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.employee.utils.EmailUtil.UPDATE_PASS_MAIL_URL;
import static com.employee.utils.EmailUtil.UPDATE_PASS_SUBJECT;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${mail.base.url}")
    private String rootPath;

    @Value("${mail.updatepassword.template}")
    private String updatePasswordTemplateLocation;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendUpdatePasswordEmail(User user) {

        String url = String.format(UPDATE_PASS_MAIL_URL, this.rootPath, user.getId());
        System.out.println(url);
        String mailBody = EmailGenerator.generateMailWithUsername(user.getFullname(), url, updatePasswordTemplateLocation);
        emailSenderService.sendMail(user.getEmail(), UPDATE_PASS_SUBJECT, mailBody);
        System.out.println("mail sent succefully to " + user.getEmail());
    }
}
