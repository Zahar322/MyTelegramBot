package com.service.serviceImpl;

import com.entity.City;
import com.myAnno.InjectRandomString;
import com.repo.CityRepo;
import com.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {


    @Autowired
    //@Qualifier("string")
    private String randomString;

    @Autowired
    private CityRepo cityRepo;

    //@Autowired
    public CityServiceImpl( @Qualifier("string") String randomString) {
        this.randomString = randomString;
    }

    @Override
    @Transactional
    public City save(City city) {
        if(verifyUniqueCity(city))return cityRepo.save(city);
        return null;
    }

    @Override
    public String toString() {
        return "CityServiceImpl{" +
                "randomString='" + randomString + '\'' +
                ", cityRepo=" + cityRepo +
                '}';
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

    @Override
    public Iterable<City> findAll() {
        System.out.println(randomString);
        return cityRepo.findAll();
    }

    @Override
    public List<City> findCityByOrderByIdDesc() {
        return cityRepo.findCityByOrderByIdDesc();
    }
}
