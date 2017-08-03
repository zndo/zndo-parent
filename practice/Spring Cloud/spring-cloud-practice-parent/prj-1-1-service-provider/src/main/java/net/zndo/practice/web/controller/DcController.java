package net.zndo.practice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DiscoveryClient-DC
 * @author zndo
 *
 */
@RestController
public class DcController {

	@Autowired
	DiscoveryClient dc;
	
	@GetMapping("/dc")
	public String dc() {
		String services = "Services: " + dc.getServices();
		System.out.println(services);
		return services;
	}
	
}
