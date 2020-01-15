package com;

import com.entity.City;
import com.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest",produces = "application/json")
public class ApplicationRestController {

    @Autowired
    private CityService cityService;

    @GetMapping("/city/{cityName}")
    public City getCityByName(@PathVariable String cityName){
        return cityService.findByCityName(cityName);
    }

    @PostMapping("/save")
    public City saveCity(@RequestBody City city){
        return cityService.save(city);
    }

    @PutMapping("/update")
    public City updateCity(@RequestBody City city){
        return cityService.update(city);
    }

    @DeleteMapping("/delete")
    public void deleteCity(@RequestBody City city){
        cityService.deleteByCityName(city.getCityName());
    }
}
