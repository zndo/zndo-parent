package net.zndo.admin.web.filters;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.zndo.admin.commons.util.JsoupUtils;

/**
 * Github 验证过滤器
 * 
 * @author zndo
 *
 */
public class GithubAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	// 获取 Token 的 API
	private final static String accessTokenUri = "https://github.com/login/oauth/access_token";

	// client_id-客户端 ID
	private static final String clientId = "312af1bd2c59b9385dd7";

	// client_secret-客户端密钥
	private final static String clientSecret = "283d7d9fbc0657b232f2c5491b54b4cc4d3b7504";

	// redirect_uri-重定向（回调）地址
	private final static String redirectUri = "http://www.test.net/signin/github";

	public GithubAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(new AntPathRequestMatcher(defaultFilterProcessesUrl, "GET"));
	}

	/**
	 * 获取 Github Token
	 * 
	 * @param code
	 * @param state
	 * @return
	 * @throws IOException
	 */
	private GithubToken getGithubToken(String code, String state) throws IOException {
		Document document = JsoupUtils.getDocWithPC(accessTokenUri + "?client_id=" + clientId + "&code=" + code
				+ "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&state=" + state);
		String tokenResult = document.text();
		String[] results = tokenResult.split("&");
		if (results.length == 3) {
			GithubToken githubToken = new GithubToken();
			String accessToken = results[0].replace("access_token=", "");
			String scope = results[1].replace("scope=", "");
			String tokenType = results[2].replace("token_type=", "");
			githubToken.setAccessToken(accessToken);
			githubToken.setScope(scope);
			githubToken.setTokenType(tokenType);
			return githubToken;
		}
		return null;
	}

	/**
	 * 尝试进行验证
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		GithubToken githubToken = this.getGithubToken(code, state);
		if (githubToken != null) {
			// 生成验证 authenticationToken
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					githubToken.getAccessToken(), githubToken.getScope());
			// 返回验证结果
			return this.getAuthenticationManager().authenticate(authRequest);
		}
		return null;
	}

	/**
	 * Github Token 类 接收 Github 验证返回数据
	 * 
	 * @author zndo
	 *
	 */
	class GithubToken {

		/**
		 * token
		 */
		private String accessToken;

		/**
		 * 范围
		 */
		private String scope;

		/**
		 * token 类型
		 */
		@SuppressWarnings("unused")
		private String tokenType;

		String getAccessToken() {
			return accessToken;
		}

		void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		private String getScope() {
			return scope;
		}

		private void setScope(String scope) {
			this.scope = scope;
		}

		private void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}
	}

}
