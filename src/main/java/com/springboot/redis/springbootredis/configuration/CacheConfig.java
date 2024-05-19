package com.springboot.redis.springbootredis.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheConfig {

    public static final String CACHE_CATEGORY_1 = "cache_category_1";
    public static final String CAR_APP = "car_app";

    Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    private final CacheTtlConfig redisCacheManagerConfig;

    public CacheConfig(final CacheTtlConfig cacheTtlConfigValue) {
        this.redisCacheManagerConfig = cacheTtlConfigValue;
    }

    @Bean(name = "cacheManager")
    public RedisCacheManager cacheManager(final RedisConnectionFactory connectionFactory) {
        logger.info("Initializing  Redis Cache manager...");
        RedisCacheConfiguration redisCacheOne = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(Long.parseLong(redisCacheManagerConfig.getCacheCatOneTtlInMin())));
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put(CACHE_CATEGORY_1, redisCacheOne);
        logger.info("Setting {} time to live to {} mins", CACHE_CATEGORY_1, redisCacheManagerConfig.getCacheCatOneTtlInMin());
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }
}
