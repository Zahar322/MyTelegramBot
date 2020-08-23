package com.commands;

import com.entity.City;
import com.myAnno.InjectRandomInt;
import com.myAnno.InjectRandomString;
import com.service.CityService;

import java.util.List;

public class ReverseWordCommand implements Command {

    @InjectRandomInt(min = 2,max = 5)
    private int random;

    @InjectRandomString(mat1 = "Ах ты " ,mat2 = "уйййййй")
    private String randomString;

    public int getRandom() {
        return random;
    }

    public String getRandomString() {
        return randomString;
    }

    @Override
    public String execute(CityService service, Message message) {
        List<City> cityList=service.findCityByOrderByIdDesc();
        StringBuilder reverse=new StringBuilder(" ");
        for(City city:cityList){
            reverse.append(city.getCityName()).reverse();
        }
        return reverse.toString();
    }

    @Override
    public String toString() {
        return "ReverseWordCommand{" +
                "random=" + random +
                ", randomString='" + randomString + '\'' +
                '}';
    }
}
