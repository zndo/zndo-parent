package net.zndo.admin.data.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 后台管理用户实体类
 * @author zndo
 *
 */
@Data
public class AdminUser implements Serializable{
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 8338694667689960502L;
	
	// ID
	private Long id;
	
	// OpenID-唯一身份标识
	private String openId;
	
	// 帐号
	private String username;
	
	// 密码
	private String password;
	
	// 登录类型
	private String signinType;
	
	// 昵称
	private String nickname;
	
	// 角色
	private String roles;
	
	// 头像
	private String avatarUri;
	
	// 性别
	private String gender;
	
	// 其他
	private String meta;
	
	// MD5
	private String md5;

}
