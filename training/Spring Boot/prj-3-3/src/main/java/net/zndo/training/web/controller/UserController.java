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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
	@ApiOperation(value = "请求获取用户数据", notes = "/user/{id}")
	@ApiImplicitParam(name = "id", value = "用户 ID", required = true, dataType = "Long", paramType="path")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User user(@PathVariable Long id) {
		return userMap.get(id);
	}

	/**
	 * 处理 "/user/list" GET 请求获取用户列表
	 * 
	 * @return
	 */
	@ApiOperation(value = "请求获取用户列表", notes = "/user/list")
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
	@ApiOperation(value = "请求创建用户", notes = "/user/create")
	@ApiImplicitParam(name = "user", value = "用户数据实体类", required = true, dataType = "User")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String userCreate(@ModelAttribute User user) {
		userMap.put(user.getId(), user);
		return "success";
	}

	/**
	 * 处理 "/user/update/{id}" PUT 请求更新用户数据
	 * 
	 * 使用了 @PathVariable 的参数，API 需要加入 paramType="path"，否则会报参数转换错误
	 * 
	 * @param id 用户ID
	 * @param user 要更新的数据
	 * @return
	 */
	@ApiOperation(value = "请求更新用户数据", notes = "/user/update/{id}")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "id", value = "用户 ID", required = true, dataType = "Long", paramType="path"),
			@ApiImplicitParam(name = "user", value = "用户数据实体类", required = true, dataType = "User") })
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
	@ApiOperation(value = "请求移除用户数据", notes = "/user/remove/{id}")
	@ApiImplicitParam(name = "id", value = "用户 ID", required = true, dataType = "Long", paramType="path")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public String userRemove(@PathVariable Long id) {
		userMap.remove(id);
		return "success";
	}

}
