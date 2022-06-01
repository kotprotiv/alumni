package it.kirill.alumni.service;

import it.kirill.alumni.repository.AlumniRepository;
import it.kirill.alumni.service.validation.ValidationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CacheInvalidationAspectTest {
    private RedisCacheManager cacheManager;
    private Cache cache;

    @BeforeEach
    void init() {
        cacheManager = mock(RedisCacheManager.class);
        cache = mock(Cache.class);
        when(cacheManager.getCache(anyString())).thenReturn(cache);
    }

    @Test
    void logBeforeMethodCall() {
        AlumniService target = new AlumniServiceImpl(
                mock(AlumniRepository.class),
                mock(AlumniDtoHelper.class),
                mock(ValidationFacade.class)
        );

        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        CacheInvalidationAspect aspect = new CacheInvalidationAspect(cacheManager);
        factory.addAspect(aspect);
        AlumniService proxy = factory.getProxy();

        proxy.save(null);

        verify(cacheManager, times(1)).getCache("alumniCache");
        verify(cache, times(1)).clear();
    }
}