package net.zndo.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import net.zndo.training.data.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj46ApplicationTests {

	@Autowired
	private StringRedisTemplate strRedisTemplate;

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() throws Exception {

		// 字符串模型存储数据
		strRedisTemplate.opsForValue().set("redistest", "valuevalue1");
		System.out.println(strRedisTemplate.opsForValue().get("redistest"));
		
		// 对象模型存储数据
		User user1 = new User(1, "九歌1");
		User user2 = new User(2, "九歌2");
		User user3 = new User(3, "九歌3");
		redisTemplate.opsForValue().set(user1.getUsername(),user1);
		redisTemplate.opsForValue().set(user2.getUsername(),user2);
		redisTemplate.opsForValue().set(user3.getUsername(),user3);
		
		System.out.println(redisTemplate.opsForValue().get("九歌1").getId());
		System.out.println(redisTemplate.opsForValue().get("九歌2").getId());
		System.out.println(redisTemplate.opsForValue().get("九歌3").getId());
		
	}

}
