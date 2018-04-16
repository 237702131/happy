package com.liangbc.happy.controller;

import com.liangbc.happy.domain.City;
import com.liangbc.happy.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author liangbc
 * @date 2017/8/28
 */
@RestController
@RequestMapping(value = "/v1/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private CityService cityService;

    @GetMapping(value = "/{id}")
    public Mono<City> findById(@PathVariable Integer id) {
        logger.info("获取城市根据id id= {}", id);
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.findCityById(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Flux<City> findAllCity() {
        logger.info("获取所有城市");
        return Flux.create(cityFluxSink -> {
            cityService.findAllCity().forEach(city -> {
                cityFluxSink.next(city);
            });
            cityFluxSink.complete();
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public Mono<Integer> createCity(@RequestBody City city) {

        logger.info("创建城市 city = {}", city.toString());

        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.saveCity(city)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Mono<Integer> modifyCity(@RequestBody City city) {
        logger.info("修改城市 city = {}", city.toString());
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.updateCity(city)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Mono<Integer> modifyCity(@PathVariable("id") Integer id) {
        logger.info("修改城市 id = {}", id);
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.deleteCity(id)));
    }
}
