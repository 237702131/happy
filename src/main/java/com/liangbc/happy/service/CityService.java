package com.liangbc.happy.service;


import com.liangbc.happy.domain.City;

import java.util.List;

/**
 * 城市业务逻辑接口类
 * <p>
 *
 * @author liangbc
 * @date 09/29/2017
 */
public interface CityService {

    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity();

    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    City findCityById(Integer id);

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Integer saveCity(City city);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    Integer updateCity(City city);

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    Integer deleteCity(Integer id);
}