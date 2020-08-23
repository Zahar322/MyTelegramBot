package com.commands;

import com.entity.City;
import com.service.CityService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitySortByNameCommand implements Command {

    @Override
    public String execute(CityService service, Message message) {
        List<City> cityList=service.findCityByOrderByIdDesc();
        Collections.sort(cityList,(Comparator.comparing(City::getCityName)));
        return cityList.toString();
    }
}
