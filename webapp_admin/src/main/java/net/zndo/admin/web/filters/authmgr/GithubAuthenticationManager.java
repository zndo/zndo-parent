package net.zndo.admin.web.filters.authmgr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.zndo.admin.commons.util.JsoupUtils;
import net.zndo.admin.data.entity.AdminUser;
import net.zndo.admin.domain.enums.SigninType;
import net.zndo.admin.service.IAdminUserService;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zndo
 *
 */
public class GithubAuthenticationManager implements AuthenticationManager {
	private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	private final IAdminUserService adminUserService;

	@Autowired
	public GithubAuthenticationManager(IAdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	/**
	 * 获取 Github 登录信息的 API 地址
	 */
	private final static String userInfoUri = "https://api.github.com/user?access_token=";

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	private AdminUser getAdminUserInfo(String accessToken) {
		Document document = JsoupUtils.getDocWithPC(userInfoUri + accessToken);
		String resultText = document.text();
		JSONObject json = JSON.parseObject(resultText);

		// 解析 Github 用户信息
		AdminUser adminUser = new AdminUser();
		adminUser.setOpenId(json.getString("id"));
		adminUser.setNickname(json.getString("login"));
		adminUser.setGender("男");
		adminUser.setAvatarUri(json.getString("avatar_url"));

		String meta = json.getString("location") + " " + json.getString("name") + " " + json.getString("company");
		adminUser.setMeta(meta);
		adminUser.setSigninType(SigninType.GITHUB.name());

		String md5 = DigestUtils.md5DigestAsHex(resultText.getBytes());
		adminUser.setMd5(md5);

		// 更新 Github 用户在本站的信息
		return adminUserService.updateAdminUser(adminUser);
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		if (auth.getName() != null && auth.getCredentials() != null) {
			AdminUser adminUser = getAdminUserInfo(auth.getName());
			return new UsernamePasswordAuthenticationToken(adminUser, null, AUTHORITIES);
		}
		throw new BadCredentialsException("Bad Credentials");
	}

}
