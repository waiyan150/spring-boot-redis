package com.springboot.redis.springbootredis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot.redis.springbootredis.configuration.CacheConfig.CAR_APP;

@Service
public class CarService {

    Logger logger = LoggerFactory.getLogger(CarService.class);

    @Cacheable(cacheNames = CAR_APP, key = "#root.methodName")
    public List<String> getAll() {
        logger.info("Fetching all records...");
        return List.of("Humvee", "Toyota", "BMW");
    }
}
