package com.springboot.redis.springbootredis.controller;


import com.springboot.redis.springbootredis.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    private final CarService carService;

    public CarController(final CarService carServiceValue) {
        carService = carServiceValue;
    }

    @GetMapping()
    List<String> getAllCars() {
        return carService.getAll();
    }
}
