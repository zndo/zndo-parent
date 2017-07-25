package net.zndo.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import net.zndo.training.data.entity.User2;
import net.zndo.training.data.mapper.User2Mapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class Prj48ApplicationTests {

	@Autowired
	private User2Mapper user2Mapper;

	@Test
	public void contextLoads() {
	}

	@Test
	@Rollback
	public void testMyBatis() throws Exception {

		int seed = (int) (Math.random() * 1000000);

		user2Mapper.insert("username" + seed, "password");

		User2 u2 = user2Mapper.findByName("username");
		System.out.println(u2.getPassword());

	}

}
