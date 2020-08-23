package com.commands;

import com.service.CityService;


public class CityCommand implements Command {

    @Override
    public String execute(CityService service, Message message) {
        return service.findAll().toString();
    }
}
