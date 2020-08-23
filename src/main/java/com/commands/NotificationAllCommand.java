package com.commands;
import com.entity.User;
import com.mail.MailService;
import com.myAnno.InjectRandomInt;
import com.myAnno.InjectRandomString;
import com.repo.UserRepo;
import com.service.CityService;

import javax.mail.MessagingException;


public class NotificationAllCommand implements Command {

    private UserRepo userRepo;

    private MailService mailService;

    @InjectRandomInt(min = 3,max = 10)
    private int random;

    public int getRandom() {
        return random;
    }

    @Override
    public String execute(CityService service, Message message) {
        String sentMessage = message.getMessage();
        Iterable<User> userList = userRepo.findAll();
        userList.forEach(user->{
            try {
                mailService.sendMessage(user.getEmail(),sentMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        return "notification fulfilled";
    }

    @Override
    public void setSenderAttribute(MailService service, UserRepo userRepo) {
        this.userRepo=userRepo;
        mailService=service;
    }

    @Override
    public String toString() {
        return "NotificationAllCommand{" +
                "userRepo=" + userRepo +
                ", mailService=" + mailService +
                ", random=" + random +
                '}';
    }
}
