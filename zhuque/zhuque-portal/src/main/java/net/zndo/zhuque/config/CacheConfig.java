package net.zndo.zhuque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 基于 ehcache 的缓存管理器，默认缓存机制
     */
    @Bean(name = "appEhCacheCacheManager")
    @Primary
    public CacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager(bean.getObject ());
    }
    
    /**
     * 基于 redis 的缓存管理器，手动配置启用
     * @return
     */
    @Bean(name = "appRedisCacheManager")
    public CacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);

        // 过期秒数，默认无限制
        redisCacheManager.setDefaultExpiration(300);

        Map<String, Long> expires = new HashMap<String, Long>();//Maps.newHashMap();
        expires.put("20SecsInfoCache", 20l);
        expires.put("10SecsInfoCache", 10l);

        redisCacheManager.setExpires(expires);
        return redisCacheManager;
    }

    /**
     * 据 shared 与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation (new ClassPathResource("conf/ehcache-app.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public KeyGenerator mzKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()).append("-");
                sb.append(method.getName()).append("-");
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}