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
 * Created by liangbc on 2017/8/28.
 */
@RestController
@RequestMapping(value = "/v1/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private CityService cityService;

    @GetMapping(value = "/{id}")
    public Mono<City> findById(@PathVariable long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.findCityById(id)));
    }

//    @GetMapping(value = "/{id}")
//    public City findById(@PathVariable long id) {
//        return cityService.findCityById(id);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public Flux<City> findAllCity() {
        return Flux.create(cityFluxSink -> {
            cityService.findAllCity().forEach(city -> {
                cityFluxSink.next(city);
            });
            cityFluxSink.complete();
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public Mono<Long> createCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.saveCity(city)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Mono<Long> modifyCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.updateCity(city)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Mono<Long> modifyCity(@PathVariable("id") Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.deleteCity(id)));
    }
}
