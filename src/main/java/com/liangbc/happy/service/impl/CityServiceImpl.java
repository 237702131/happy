package com.liangbc.happy.service.impl;

import com.liangbc.happy.domain.City;
import com.liangbc.happy.service.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 23770
 */
@Service
public class CityServiceImpl implements CityService {

    private static Map<Integer, City> CITY_DB = new HashMap<>();

    static {
        City city = new City();
        city.setId(1);
        city.setDescription("城市描述");
        city.setCityName("北京");
        CITY_DB.put(1, city);
        city = new City();
        city.setId(2);
        city.setDescription("城市描述");
        city.setCityName("北京");
        CITY_DB.put(2, city);
    }

    @Override
    public List<City> findAllCity() {
        return new ArrayList<>(CITY_DB.values());
    }

    @Override
    public City findCityById(Integer id) {
        return CITY_DB.get(id);
    }

    @Override
    public Integer saveCity(City city) {
        city.setId(CITY_DB.size() + 1);
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    @Override
    public Integer updateCity(City city) {
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    @Override
    public Integer deleteCity(Integer id) {
        CITY_DB.remove(id);
        return id;
    }

}