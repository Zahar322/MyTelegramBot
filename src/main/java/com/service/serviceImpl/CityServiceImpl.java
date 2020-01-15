package com.service.serviceImpl;

import com.entity.City;
import com.repo.CityRepo;
import com.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    @Transactional
    public City save(City city) {
        if(verifyUniqueCity(city))return cityRepo.save(city);
        return null;
    }

    @Override
    @Transactional
    public City update(City city) {
        return cityRepo.save(city);
    }

    @Override
    @Transactional
    public void deleteByCityName(String cityName) {
        cityRepo.deleteByCityName(cityName);
    }

    @Override
    public City findByCityName(String cityName) {
        return cityRepo.findByCityName(cityName);
    }

    @Override
    public boolean verifyUniqueCity(City city) {
        return cityRepo.findByCityName(city.getCityName())==null;
    }
}
