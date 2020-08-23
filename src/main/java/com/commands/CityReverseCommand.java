package com.commands;


import com.service.CityService;

public class CityReverseCommand implements Command {

    @Override
    public String execute(CityService service, Message message) {
        return service.findCityByOrderByIdDesc().toString();
    }
}
