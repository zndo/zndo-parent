package net.zndo.zhuque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.zndo.zhuque.service.CacheService;

@RestController
@RequestMapping("/cache")
public class CacheController {

	@Autowired
	private CacheService cacheService;

	@GetMapping("/cache1")
	public String cache1() {
		return cacheService.fetchCurrentTime();
	}

	@GetMapping("/cache2")
	public String cache2() {
		return cacheService.fetchCurrentTimeWithRedisCache();
	}

	@GetMapping("/cache3")
	public String cache3() {
		return cacheService.fetchCurrentTimeWithKeyGenerator();
	}

	@GetMapping("/refreshCache")
	public String refreshCache() {
		return cacheService.refreshCache();
	}

	@GetMapping("/refreshAll")
	public String refreshAll() {
		return cacheService.refreshAllCache();
	}

}
