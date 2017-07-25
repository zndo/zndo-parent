package net.zndo.zhuque.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import net.zndo.zhuque.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

	@Override
	public String fetchCurrentTime() {
		return (new Date()).toString();
	}

	@Override
	public String fetchCurrentTimeWithRedisCache() {
		return (new Date()).toString();
	}

	@Override
	public String fetchCurrentTimeWithKeyGenerator() {
		return "KeyGenerator: " + (new Date()).toString();
	}

	@Override
	public String refreshCache() {
		return "已移除";
	}

	@Override
	public String refreshAllCache() {
		return "刷新所有缓存";
	}

}
