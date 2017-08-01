package net.zndo.training.service;

import net.zndo.training.data.entity.User;

public interface IUserService {

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	boolean insert(User user);
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
}
