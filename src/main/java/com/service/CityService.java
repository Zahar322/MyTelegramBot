package com.service;

import com.entity.City;

public interface CityService {

    City save(City city);

    City update(City city);

    void deleteByCityName(String cityName);

    City findByCityName(String cityName);

    boolean verifyUniqueCity(City city);
}
