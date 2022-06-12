package com.example.pfe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to  ){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("hneee lmail mteeek");
        message.setTo(to);
        message.setText("we are happy to announce you that your account has been activated successfully... looking forward to see you ");
        message.setSubject("account activated ");
        mailSender.send(message);
        System.out.println("Mail sent successfully...");
    }


}
