package net.zndo.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Prj91Application {

	public static void main(String[] args) {
		SpringApplication.run(Prj91Application.class, args);
	}
}
