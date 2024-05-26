package com.springboot.redis.springbootredis.service;

import io.lettuce.core.RedisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.List;

import static com.springboot.redis.springbootredis.configuration.CacheConfig.CAR_APP;

@Service
public class CarService {

    Logger logger = LoggerFactory.getLogger(CarService.class);

    @Cacheable(cacheNames = CAR_APP, key = "#root.methodName", sync = true)
    @Retryable(retryFor = ConnectException.class, maxAttempts = 1, recover = "getAllRecover")
    @CircuitBreaker(retryFor = {RedisConnectionException.class},
            maxAttempts = 2,
            openTimeout = 2000L,
            resetTimeout = 20000L)
    public List<String> getAll() {
        logger.info("Fetching all records...");
        return List.of("Humvee", "Toyota", "BMW");
    }

    @Recover
    public List<String> getAllRecover() {
        return this.getAll();
    }
}
