package com.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@PropertySource("classpath:mail.properties")
public class MailService {

    @Value("${username}")
    private String from;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private MimeMessage mimeMessage;

    @Autowired
    private MimeMessageHelper mimeMessageHelper;

    public void sendMessage(String receiver,String message) throws MessagingException {
        mimeMessageHelper.setFrom("Agentbullshita@yandex.by");
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setText(message,false);
        mailSender.send(mimeMessage);
    }
}
