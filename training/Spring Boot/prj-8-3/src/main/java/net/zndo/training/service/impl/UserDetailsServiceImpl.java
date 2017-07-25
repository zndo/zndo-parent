package net.zndo.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.zndo.training.data.entity.User;
import net.zndo.training.service.IUserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	private IUserService userService;

	@Autowired
	UserDetailsServiceImpl(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在！");
		}
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(user.getRoles());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorities);
	}

	/**
	 * 权限字符串转化
	 *
	 * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") +
	 * SimpleGrantedAuthority("ADMIN")
	 *
	 * @param roleStr
	 *            权限字符串
	 */
	private List<SimpleGrantedAuthority> createAuthorities(String roleStr) {
		String[] roles = roleStr.split(",");
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		for (String role : roles) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
		}
		return simpleGrantedAuthorities;
	}

}
