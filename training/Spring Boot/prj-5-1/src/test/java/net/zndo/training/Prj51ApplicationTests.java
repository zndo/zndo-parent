package net.zndo.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.zndo.training.data.entity.User2;
import net.zndo.training.data.repository.User2Repository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj51ApplicationTests {

	@Autowired
	private User2Repository user2Repository;

	@Test
	public void contextLoads() {
	}

	@Test
	// 使用默认事务级别(Isolation.DEFAULT = READ_COMMITTED)
	// @Transactional(propagation=Propagation.REQUIRED)
	@Transactional(isolation = Isolation.DEFAULT) // 数据新增时产生异常将回滚数据
	// : Rolled back transaction for test context
	public void dataJpaTest() throws Exception {

		int seed = (int) (Math.random() * 1000000);

		// 查阅建表 SQL 语句可见
		// `username` varchar(16) NOT NULL COMMENT '用户名',
		// 当创建用户数据时如果[username-用户名]长度超过 16
		// （或者通过定义属性值长度 @see net.zndo.training.data.entity.User2:line 23），
		// 即会发生异常；当我们希望这些数据是同一批次新增成功或失败，就需要通过事务处理来实现

		// 创建记录
		// batch{
		user2Repository.save(new User2("user" + seed, "pass" + seed, "emmail" + seed + "@mail.com"));
		user2Repository.save(new User2("user" + (seed + 1), "pass" + (seed + 1), "emmail" + (seed + 1) + "@mail.com"));
		user2Repository.save(new User2("user" + (seed + 2), "pass" + (seed + 2), "emmail" + (seed + 2) + "@mail.com"));
		// : SQL Error: 1406, SQLState: 22001
		// : Data truncation: Data too long for column 'username' at row 1
		user2Repository.save(
				new User2("user1111111111" + (seed + 3), "pass" + (seed + 3), "emmail" + (seed + 3) + "@mail.com"));
		user2Repository.save(new User2("user" + (seed + 4), "pass" + (seed + 4), "emmail" + (seed + 4) + "@mail.com"));
		// }

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
