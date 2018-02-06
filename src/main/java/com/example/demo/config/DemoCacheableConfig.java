package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Slf4j
public class DemoCacheableConfig {

  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager("users", "employees");
  }
}
