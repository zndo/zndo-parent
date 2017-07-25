package net.zndo.training.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.zndo.training.domain.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>());

	/**
	 * 处理 "/user/{id}" GET 请求获取用户数据
	 * 
	 * @param id
	 *            用户 ID
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User user(@PathVariable Long id) {
		return userMap.get(id);
	}

	/**
	 * 处理 "/user/list" GET 请求获取用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<User> userList() {
		List<User> lstUser = new ArrayList<User>(userMap.values());
		return lstUser;
	}

	/**
	 * 处理 "/user/create" POST 请求创建用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String userCreate(@ModelAttribute User user) {
		userMap.put(System.currentTimeMillis(), user);
		return "success";
	}

	/**
	 * 处理 "/user/update/{id}" PUT 请求更新用户数据
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public String userUpdate(@PathVariable Long id, @ModelAttribute User user) {
		// 根据用户 ID 获取用户数据
		User targetUser = userMap.get(id);
		// 更新数据
		targetUser.setName(user.getName());
		targetUser.setAge(user.getAge());
		// 持久化
		userMap.put(id, targetUser);
		return "sucess";
	}

	/**
	 * 处理 "/user/remove/{id}" DELETE 请求移除用户数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public String userRemove(@PathVariable Long id) {
		userMap.remove(id);
		return "success";
	}

}
