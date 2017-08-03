package net.zndo.admin.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import groovy.util.logging.Log4j2;
import lombok.AllArgsConstructor;
import net.zndo.admin.commons.constants.RoleConstant;
import net.zndo.admin.data.entity.AdminUser;
import net.zndo.admin.data.repository.AdminUserMapper;
import net.zndo.admin.service.IAdminUserService;

@Service
@Primary
@AllArgsConstructor
@Log4j2
public class AdminUserServiceImpl implements IAdminUserService {

	private AdminUserMapper adminUserMapper;

	@Override
	public AdminUser updateAdminUser(AdminUser adminUser) {
		return null;
	}

	@Override
	public AdminUser getAdminUserById(Long id) {
		return adminUserMapper.selectAdminUserById(id);
	}

	@Override
	public AdminUser getAdminUserByUsername(String username) {
		return adminUserMapper.selectAdminUserByUsername(username);
	}

	@Override
	public boolean addAdminUser(AdminUser adminUser) {

		// 检查用户名是否存在
		if (chkUsernameExist(adminUser.getUsername())) {
			return false;
		}

		// 密码加密，存储到数据库为密文
		encryptPassword(adminUser);
		// 设置默认角色
		adminUser.setRoles(RoleConstant.ROLE_USER);

		// 新增后台管理用户
		int result = adminUserMapper.insertAdminUser(adminUser);
		return (result == 1);
	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean chkUsernameExist(String username) {
		AdminUser user = adminUserMapper.selectAdminUserByUsername(username);
		return (user != null);
	}

	/**
	 * 对密码进行加密
	 */
	private void encryptPassword(AdminUser adminUser) {
		String password = adminUser.getPassword();
		password = new BCryptPasswordEncoder().encode(password);
		adminUser.setPassword(password);
	}

}
