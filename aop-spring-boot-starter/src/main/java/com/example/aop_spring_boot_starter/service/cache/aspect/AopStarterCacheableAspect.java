package com.example.aop_spring_boot_starter.service.cache.aspect;

import com.example.aop_spring_boot_starter.exception.AopStarterCacheManagementException;
import com.example.aop_spring_boot_starter.service.cache.annotation.Cacheable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Aspect
public class AopStarterCacheableAspect {
    private final RedisTemplate<String, Object> cacheService;

    public AopStarterCacheableAspect(RedisTemplate<String, Object> cacheService) {
        this.cacheService = cacheService;
    }

    @Around("@annotation(com.example.aop_spring_boot_starter.service.cache.annotation.Cacheable)")
    public Object getCachedData(ProceedingJoinPoint joinPoint) throws Throwable {
        String cacheKey = ((MethodSignature) joinPoint.getSignature()).getReturnType().getName();
        String hashKey = String.valueOf(Objects.hash(joinPoint.getSignature(), Arrays.hashCode(joinPoint.getArgs())));

        Object cachedObject = cacheService.opsForHash().get(cacheKey, hashKey);
        if (cachedObject != null) {
            return cachedObject;
        }

        Object originalObject = joinPoint.proceed();
        if (!(originalObject instanceof Serializable)) {
            throw new AopStarterCacheManagementException(String.format("cacheable object is not serializable: %s", originalObject.getClass().getSimpleName()));
        }

        cacheService.opsForHash().put(cacheKey, hashKey, originalObject);
        return originalObject;
    }
}
