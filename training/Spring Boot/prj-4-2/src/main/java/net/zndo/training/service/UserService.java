package net.zndo.training.service;

import java.util.List;

import net.zndo.training.data.entity.User;

public interface UserService {
	
	User user(Integer id);
	
	List<User> userList();

	int create(User user);
	
	int update(User user);
	
	int remove(Integer id);
	
}
