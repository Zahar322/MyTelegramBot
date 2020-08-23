package com.service;

import com.entity.City;

import java.util.List;

public interface CityService {

    City save(City city);

    City update(City city);

    void deleteByCityName(String cityName);

    City findByCityName(String cityName);

    boolean verifyUniqueCity(City city);

    Iterable<City> findAll();

    List<City> findCityByOrderByIdDesc();
}
