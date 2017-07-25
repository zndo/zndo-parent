package net.zndo.training;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import net.zndo.training.data.entity.User2;
import net.zndo.training.data.repository.User2Repository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj91ApplicationTests {

	@Autowired
	private User2Repository user2Repository;

	@Autowired
	private CacheManager cache;

	@Test
	public void contextLoads() {
	}

	@Before
	public void before() {
//		user2Repository.save(new User2("userCacheTest", "passuserCacheTest", "emmail@mail.com"));
	}

	@Test
	public void dataJpaTest() throws Exception {

		// 根据姓名查询
		try {
			User2 u1 = user2Repository.findUserByUsername("userCacheTest");
			System.out.println(u1.getUsername());

			User2 u2 = user2Repository.findUserByUsername("userCacheTest");
			System.out.println(u2.getUsername());
		} catch (javax.persistence.NonUniqueResultException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
