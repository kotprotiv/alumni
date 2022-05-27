package it.kirill.alumni.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.redis.cache.RedisCacheManager;

@Slf4j
@Aspect
public class CacheInvalidationAspect {

    private final RedisCacheManager cacheManager;

    public CacheInvalidationAspect(RedisCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Before("execution(* it.kirill.alumni.service.AlumniServiceImpl.save(..))")
    public void logBeforeMethodCall() {
        log.debug("Invalidating cache");
        cacheManager.getCache("alumniCache").clear();
    }
}
