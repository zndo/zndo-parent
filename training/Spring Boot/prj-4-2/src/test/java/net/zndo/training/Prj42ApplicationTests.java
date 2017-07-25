package net.zndo.training;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.zndo.training.data.entity.User;
import net.zndo.training.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj42ApplicationTests {

	@Autowired
	private UserService userService;

	@Before
	public void setup() {
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void userCreateTest() throws Exception {

		int seed = (int) (Math.random() * 1000000);

		// 创建一个新用户
		User user = new User();
		user.setUsername("user" + seed);// 最大长度16个字符
		user.setPassword("password" + seed);
		user.setEmail("user" + seed + "@test.com");
		userService.create(user);

	}

	@Test
	public void userListTest() throws Exception {
		Assert.assertEquals(14, userService.userList().size());
	}

	@Test
	public void userUpdateTest() throws Exception {

		// 获取指定用户
		User userForUpdate = userService.user(6);

		// 更新用户信息
		userForUpdate.setUsername("usernameU");
		userForUpdate.setPassword("passwordU");
		userForUpdate.setEmail("updateuser1@test.com");
		userService.update(userForUpdate);

	}

	@Test
	public void userRemoveTest() throws Exception {

		// 删除一个用户
		userService.remove(1);

	}

}
