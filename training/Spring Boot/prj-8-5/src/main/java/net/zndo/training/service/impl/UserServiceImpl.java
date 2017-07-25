package net.zndo.training.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.zndo.training.constants.RoleConstant;
import net.zndo.training.data.entity.User;
import net.zndo.training.data.repository.UserMapper;
import net.zndo.training.service.IUserService;

@Service
@Primary
public class UserServiceImpl implements IUserService {

	private UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper =  userMapper;
	}
	
	@Override
	public boolean insert(User user) {
		
		if(exist(user.getUsername())) {
			return false;
		}
		
		encryptPassword(user);
		user.setRoles(RoleConstant.ROLE_USER);
		int result = userMapper.insert(user);
		return (result == 1);
	}

	@Override
	public User getUserByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	public boolean exist(String username) {
		User user = userMapper.selectByUsername(username);
		return (user != null);
	}
	
    /**
     * 加密密码
     */
    private void encryptPassword(User  user){
        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);
    }

}
