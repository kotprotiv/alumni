package it.kirill.alumni.config;

import it.kirill.alumni.service.CacheInvalidationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.cache.RedisCacheManager;

@EnableAspectJAutoProxy
@Configuration
public class AspectConfig {

    @Bean
    public CacheInvalidationAspect cacheInvalidationAspect(RedisCacheManager cacheManager) {
        return new CacheInvalidationAspect(cacheManager);
    }
}
