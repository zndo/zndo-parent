package net.zndo.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Prj11ServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Prj11ServiceProviderApplication.class, args);
	}
}
