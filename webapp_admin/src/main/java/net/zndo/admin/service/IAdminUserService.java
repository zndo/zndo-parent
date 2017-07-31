package net.zndo.admin.service;

import net.zndo.admin.data.entity.AdminUser;

public interface IAdminUserService {
	
	/**
	 * 新增后台管理用户
	 * @param adminUser
	 * @return
	 */
	boolean addAdminUser(AdminUser adminUser);
	
	/**
	 * 使用第三方帐号登录时，更新本地后台管理用户
	 * @param adminUser
	 * @return
	 */
	AdminUser updateAdminUser(AdminUser adminUser);
	
	/**
	 * 获取后台管理用户
	 * @param id-ID
	 * @return
	 */
	AdminUser getAdminUserById(Long id);

	/**
	 * 获取后台管理用户
	 * @param username-帐号
	 * @return
	 */
	AdminUser getAdminUserByUsername(String username);

}
