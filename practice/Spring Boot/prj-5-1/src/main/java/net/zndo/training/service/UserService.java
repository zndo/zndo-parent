package net.zndo.training.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.zndo.training.data.entity.User2;

public interface UserService {

	// 多数时候我们在业务逻辑层进行业务处理
	@Transactional(isolation = Isolation.DEFAULT)
	User2 signin(String username, String password);
}
