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

@Aspect
public class AopStarterCacheableAspect {
    private final RedisTemplate<String, Object> cacheService;

    public AopStarterCacheableAspect(RedisTemplate<String, Object> cacheService) {
        this.cacheService = cacheService;
    }

    @Around("@annotation(cacheable)")
    public Object getCachedData(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        String cacheKey = cacheable.name().isEmpty() ? ((MethodSignature) joinPoint.getSignature()).getReturnType().getSimpleName() : cacheable.name();
        String hashKey = buildHashKey(joinPoint);
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

    private String buildHashKey(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append(joinPoint.getSignature().toShortString());
        for (Object sub : joinPoint.getArgs()) {
            String subString = sub == null ? "null" : sub.toString();
            sb.append(subString);
        }
        return sb.toString();
    }
}
