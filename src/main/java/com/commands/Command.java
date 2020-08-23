package com.commands;

import com.mail.MailService;
import com.repo.UserRepo;
import com.service.CityService;

public interface Command {

    String execute(CityService service, Message message);

    default void setSenderAttribute(MailService service, UserRepo userRepo){

    }
}
