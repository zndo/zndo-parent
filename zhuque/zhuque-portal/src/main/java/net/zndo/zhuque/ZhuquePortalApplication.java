package net.zndo.zhuque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class ZhuquePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhuquePortalApplication.class, args);
	}
}
