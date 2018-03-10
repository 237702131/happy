package com.liangbc.happy.service.impl;

import com.liangbc.happy.domain.City;
import com.liangbc.happy.service.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 09/29/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    // 模拟数据库，存储 City 信息
    private static Map<Long, City> CITY_DB = new HashMap<>();
    static {
        City city = new City();
        city.setId(1L);
        city.setDescription("城市描述");
        city.setCityName("北京");
        CITY_DB.put(1L,city);
        city = new City();
        city.setId(2L);
        city.setDescription("城市描述");
        city.setCityName("北京");
        CITY_DB.put(2L,city);
    }

    @Override
    public List<City> findAllCity() {
        return new ArrayList<>(CITY_DB.values());
    }

    @Override
    public City findCityById(Long id) {
        return CITY_DB.get(id);
    }

    @Override
    public Long saveCity(City city) {
        city.setId(CITY_DB.size() + 1L);
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    @Override
    public Long updateCity(City city) {
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    @Override
    public Long deleteCity(Long id) {
        CITY_DB.remove(id);
        return id;
    }

}