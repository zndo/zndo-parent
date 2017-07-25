package net.zndo.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.zndo.training.data.entity.User2;
import net.zndo.training.data.repository.User2Repository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj43ApplicationTests {

	@Autowired
	private User2Repository user2Repository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void dataJpaTest() throws Exception {

		int seed = (int) (Math.random() * 1000000);

		// 创建记录
		user2Repository.save(new User2("user" + seed, "pass" + seed, "emmail" + seed + "@mail.com"));
		user2Repository.save(new User2("user" + (seed + 1), "pass" + (seed + 1), "emmail" + (seed + 1) + "@mail.com"));
		user2Repository.save(new User2("user" + (seed + 2), "pass" + (seed + 2), "emmail" + (seed + 2) + "@mail.com"));
		user2Repository.save(new User2("user" + (seed + 3), "pass" + (seed + 3), "emmail" + (seed + 3) + "@mail.com"));
		user2Repository.save(new User2("user" + (seed + 4), "pass" + (seed + 4), "emmail" + (seed + 4) + "@mail.com"));

		// 测试

		// 查询所有
		user2Repository.findAll().size();

		// 根据姓名查询
		user2Repository.findUserByUsername("user1");

		// 根据姓名和年龄查询
		user2Repository.findUserByUsernameAndEmail("user1", "user1@mail.com");

		// 根据 @Query 语句查询
		user2Repository.findUser("name");

		// 删除一条记录
		User2 user2 = user2Repository.findUserByUsername("user88000");

		if (user2 == null) {
			System.out.println("数据不存在，无法删除");
			return;
		}
		user2Repository.delete(user2);

	}

}
