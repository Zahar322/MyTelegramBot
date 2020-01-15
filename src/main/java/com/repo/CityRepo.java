package com.repo;

import com.entity.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<City,Integer> {

    City findByCityName(String cityName);

    void deleteByCityName(String city);
}
