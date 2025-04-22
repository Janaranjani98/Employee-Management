package com.employeemanagement.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

//    private final MailSender mailSender;
//
//    public EmailService(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    @Async
    public void sendEmail(String to, String subject, String body){
        System.out.println("Sending email to " +to+ " on thread" +Thread.currentThread().getName());
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Email sent to " +to);
    }

//    @Async
//    public void sendEmail(String to,String subject,String body){
//        SimpleMailMessage message =new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//
//        mailSender.send(message);
//        System.out.println("Email sent to " +to);
//    }
}
