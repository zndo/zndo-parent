package net.zndo.admin.data.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import net.zndo.admin.data.entity.AdminUser;

@Mapper
@Repository
public interface AdminUserMapper {

	/**
	 * 新增后台管理用户
	 * 
	 * @param adminUser
	 * @return
	 */
	@Insert("INSERT INTO admin_user (" + 
			"	id," + 
			"	openid," + 
			"	username," + 
			"	PASSWORD," + 
			"	signin_type," + 
			"	nickname," + 
			"	roles," + 
			"	avatar_url," + 
			"	gender," + 
			"	meta," + 
			"	md5" + 
			")" + 
			"VALUES" + 
			"	(" + 
			"		#{id}," + 
			"		#{openId}," + 
			"		#{username}," + 
			"		#{password}," + 
			"		#{signinType}," + 
			"		#{nickname}," + 
			"		#{roles}," + 
			"		#{avatarUri}," + 
			"		#{gender}," + 
			"		#{meta}," + 
			"		#{md5})" + 
			"")
	int insertAdminUser(AdminUser adminUser);

	/**
	 * 使用第三方帐号登录时，更新本地后台管理用户
	 * 
	 * @param adminUser
	 * @return
	 */
	AdminUser updateAdminUser(AdminUser adminUser);

	/**
	 * 获取后台管理用户
	 * 
	 * @param id-ID
	 * @return
	 */
	@Select("SELECT * FROM admin_user AS u WHERE u.id = #{id}")
	AdminUser selectAdminUserById(@Param("id") Long id);

	/**
	 * 获取后台管理用户
	 * 
	 * @param username-帐号
	 * @return
	 */
	@Select("SELECT * FROM admin_user AS u WHERE u.username = #{username}")
	AdminUser selectAdminUserByUsername(@Param("username") String username);

}
