package com.example.aop_spring_boot_starter.configuration.redis;

import com.example.aop_spring_boot_starter.configuration.redis.environment.AopStarterRedisEnvironment;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@EnableConfigurationProperties(AopStarterRedisEnvironment.class)
public class AopStarterRedisConfiguration {
    @Bean
    public RedisConnectionFactory redisConnectionFactory(AopStarterRedisEnvironment properties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(properties.getPassword()));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory, AopStarterRedisEnvironment properties) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(properties.getLifetime()))
                .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
