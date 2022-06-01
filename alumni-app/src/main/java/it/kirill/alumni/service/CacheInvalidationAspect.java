package it.kirill.alumni.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;

@Slf4j
@Aspect
public class CacheInvalidationAspect {

    private final RedisCacheManager cacheManager;

    public CacheInvalidationAspect(RedisCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @After("execution(* it.kirill.alumni.service.AlumniServiceImpl.save(..)) ||" +
            "execution(* it.kirill.alumni.service.AlumniServiceImpl.saveAll(..))")
    public void invalidate() {
        log.debug("Invalidating cache");
        Cache alumniCache = cacheManager.getCache("alumniCache");
        if (alumniCache != null) {
            alumniCache.clear();
        }
    }
}
