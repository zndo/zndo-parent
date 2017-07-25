package net.zndo.zhuque.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

public interface CacheService {

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	@Cacheable(value = "20SecsInfoCache", key = "'cache1'")
	public String fetchCurrentTime();

	/**
	 * 使用 redis 缓存获取当前时间
	 * 
	 * @return
	 */
	@Cacheable(value = "20SecsInfoCache", key = "'cache1'", cacheManager = "appRedisCacheManager")
	public String fetchCurrentTimeWithRedisCache();

	/**
	 * 使用 KeyGenerator 获取当前时间
	 * 
	 * @return
	 */
	@Cacheable(value = "10SecsInfoCache", keyGenerator = "mzKeyGenerator")
	public String fetchCurrentTimeWithKeyGenerator();

	/**
	 * 刷新指定缓存
	 * 
	 * @return
	 */
	@CacheEvict(value = "20SecsInfoCache", key = "'cache1'")
	public String refreshCache();

	/**
	 * 刷新所有缓存
	 * 
	 * @return
	 */
	@Caching(evict = { @CacheEvict(value = "20SecsInfoCache", allEntries = true, cacheManager = "appRedisCacheManager"),
			@CacheEvict(value = "10SecsInfoCache", allEntries = true, cacheManager = "appRedisCacheManager"),
			@CacheEvict(value = "20SecsInfoCache", allEntries = true, cacheManager = "appEhCacheCacheManager"),
			@CacheEvict(value = "10SecsInfoCache", allEntries = true, cacheManager = "appEhCacheCacheManager") })
	public String refreshAllCache();

}
