package com.example.aop_spring_boot_starter.configuration.aop;

import com.example.aop_spring_boot_starter.service.cache.aspect.AopStarterCacheableAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

public class AopConfiguration {
    @Bean
    public AopStarterCacheableAspect cacheableAspect(RedisTemplate<String, Object> redisTemplate) {
        return new AopStarterCacheableAspect(redisTemplate);
    }
}
