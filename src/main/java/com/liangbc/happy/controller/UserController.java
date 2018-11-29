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

    @Resource
    private CityService cityService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/{id}")
    public Mono<City> findById(@PathVariable Integer id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.findCityById(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Flux<City> findAllCity() {
        logger.info("获取全部城市");
        return Flux.create(cityFluxSink -> {
            cityService.findAllCity().forEach(city -> {
                logger.info("city = {}",city.toString());
                cityFluxSink.next(city);
            });
            cityFluxSink.complete();
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public Mono<Integer> createCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.saveCity(city)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Mono<Integer> modifyCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.updateCity(city)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Mono<Integer> modifyCity(@PathVariable("id") Integer id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.deleteCity(id)));
    }
}
