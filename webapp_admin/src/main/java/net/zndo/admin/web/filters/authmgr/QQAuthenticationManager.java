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

import static net.zndo.admin.web.filters.QQAuthenticationFilter.clientId;

/**
 * net.zndo.admin.web.filters.authmgr
 * 
 * @author zndo
 *
 */
public class QQAuthenticationManager implements AuthenticationManager {
	private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	private final IAdminUserService adminUserService;

	@Autowired
	public QQAuthenticationManager(IAdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	// 获取 QQ 登录信息的 API 地址
	private final static String userInfoUri = "https://graph.qq.com/user/get_user_info";

	// 获取 QQ 用户信息的地址拼接
	private final static String USER_INFO_API = "%s?access_token=%s&oauth_consumer_key=%s&openid=%s";

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	private AdminUser getUserInfo(String accessToken, String openId) {
		String url = String.format(USER_INFO_API, userInfoUri, accessToken, clientId, openId);
		Document document = JsoupUtils.getDocWithPC(url);
		String resultText = document.text();
		JSONObject json = JSON.parseObject(resultText);

		// 解析 QQ 用户信息
		AdminUser adminUser = new AdminUser();
		adminUser.setOpenId(openId);
		adminUser.setNickname(json.getString("nickname"));
		adminUser.setGender(json.getString("gender"));
		adminUser.setAvatarUri(json.getString("figureurl_qq_2"));

		String meta = json.getString("year") + " " + json.getString("province");
		adminUser.setMeta(meta);
		adminUser.setSigninType(SigninType.QQ.name());

		String md5 = DigestUtils.md5DigestAsHex(resultText.getBytes());
		adminUser.setMd5(md5);

		// 更新 QQ 用户在本站的信息
		return adminUserService.updateAdminUser(adminUser);
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		if (auth.getName() != null && auth.getCredentials() != null) {
			AdminUser adminUser = getUserInfo(auth.getName(), (String) (auth.getCredentials()));
			return new UsernamePasswordAuthenticationToken(adminUser, null, AUTHORITIES);
		}
		throw new BadCredentialsException("错误的凭据");
	}

}
