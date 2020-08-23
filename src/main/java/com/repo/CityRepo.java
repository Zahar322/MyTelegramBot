package com.repo;

import com.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepo extends CrudRepository<City,Integer> {

    City findByCityName(String cityName);

    void deleteByCityName(String city);

    List<City> findCityByOrderByIdDesc();
}
