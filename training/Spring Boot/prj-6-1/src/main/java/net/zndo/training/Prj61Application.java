package net.zndo.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Prj61Application {

	public static void main(String[] args) {
		SpringApplication.run(Prj61Application.class, args);
	}
}
