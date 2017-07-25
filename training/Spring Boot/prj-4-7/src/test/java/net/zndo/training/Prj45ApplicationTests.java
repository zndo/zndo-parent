package net.zndo.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.zndo.training.data.entity.primary.User2;
import net.zndo.training.data.entity.secondary.Customer;
import net.zndo.training.data.repository.primary.User2Repository;
import net.zndo.training.data.repository.secondary.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj45ApplicationTests {

	@Autowired
	private User2Repository user2Repository;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testJdbcTemplat() throws Exception {

		int seed = (int) (Math.random() * 1000000);

		// 往数据源1新增数据
		user2Repository.save(new User2("testuser" + seed, "pass1", "user1@test.com"));
		user2Repository.save(new User2("testuser" + (seed + 2), "pass1", "user1@test.com"));
		user2Repository.save(new User2("testuser" + (seed + 3), "pass1", "user1@test.com"));
		user2Repository.save(new User2("testuser" + (seed + 4), "pass1", "user1@test.com"));
		user2Repository.save(new User2("testuser" + (seed + 5), "pass1", "user1@test.com"));

		// 往数据源1新增数据
		customerRepository.save(new Customer("customer" + seed, "13000000001"));
		customerRepository.save(new Customer("customer" + (seed + 2), "13000000002"));
		customerRepository.save(new Customer("customer" + (seed + 3), "13000000003"));

	}

}
