package net.zndo.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 使异步方法生效
public class Prj81Application {

	public static void main(String[] args) {
		SpringApplication.run(Prj81Application.class, args);
	}
}
