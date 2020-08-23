package com.commands;

import com.mail.MailService;
import com.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommandFactory {

    @Autowired
    private Map<String,Class<? extends Command>> actions;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailService service;

    public Command getCommand(String commandName){
        Class<?> command=actions.get(commandName);
        if(command!=null){
            try {
                Command calledCommand=(Command) command.newInstance();
                calledCommand.setSenderAttribute(service,userRepo);
                return calledCommand;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
