package com.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Value("${protocol}")
    private String protocol;

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl mailSender= new JavaMailSenderImpl();
        mailSender.setProtocol(protocol);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername("Agentbullshita@yandex.by");
        mailSender.setPassword(password);
        return  mailSender;

    }

    @Bean
    public MimeMessage mimeMessage(){
        MimeMessage mimeMessage=mailSender().createMimeMessage();
        return  mimeMessage;
    }

    @Bean
    public MimeMessageHelper mimeMessageHelper(){
        return new MimeMessageHelper(mimeMessage());
    }
}
