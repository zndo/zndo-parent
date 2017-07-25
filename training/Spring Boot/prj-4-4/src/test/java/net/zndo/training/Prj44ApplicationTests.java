package net.zndo.training;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj44ApplicationTests {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jt1;

	public JdbcTemplate getJt1() {
		return jt1;
	}

	public void setJt1(JdbcTemplate jt1) {
		this.jt1 = jt1;
	}

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate jt2;

	public JdbcTemplate getJt2() {
		return jt2;
	}

	public void setJt2(JdbcTemplate jt2) {
		this.jt2 = jt2;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testJdbcTemplat() throws Exception {

		int seed = (int) (Math.random() * 1000000);

		// 往数据源1新增数据
		jt1.update("insert into user2(username,password, email,create_time) values (?,?,?,?)", "testuser" + seed,
				"pass1", "user1@test.com", new Date());
		jt1.update("insert into user2(username,password, email,create_time) values (?,?,?,?)", "testuser" + (seed + 1),
				"pass1", "user1@test.com", new Date());
		jt1.update("insert into user2(username,password, email,create_time) values (?,?,?,?)", "testuser" + (seed + 2),
				"pass1", "user1@test.com", new Date());
		jt1.update("insert into user2(username,password, email,create_time) values (?,?,?,?)", "testuser" + (seed + 3),
				"pass1", "user1@test.com", new Date());

		// 往数据源2新增数据
		jt2.update("insert into user2(username,password, email,create_time) values (?,?,?,?)", "test2user" + (seed + 4),
				"pass1", "user1@test.com", new Date());

		// 检查数据源1的数据
		jt1.queryForObject("select count(1) from user2", String.class);

		// 检查数据源2的数据
		jt2.queryForObject("select count(1) from user2", String.class);

	}

}
