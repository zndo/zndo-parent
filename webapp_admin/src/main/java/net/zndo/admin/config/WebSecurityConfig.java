package net.zndo.admin.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.zndo.admin.service.IAdminUserService;
import net.zndo.admin.service.impl.AdminUserDetailsServiceImpl;
import net.zndo.admin.web.filters.GithubAuthenticationFilter;
import net.zndo.admin.web.filters.QQAuthenticationFilter;
import net.zndo.admin.web.filters.authmgr.GithubAuthenticationManager;
import net.zndo.admin.web.filters.authmgr.QQAuthenticationManager;

/**
 * Web 安全配置类
 * 
 * @author zndo
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final Log logger = LogFactory.getLog(WebSecurityConfig.class);

	private final IAdminUserService adminUserService;

	@Autowired
	public WebSecurityConfig(IAdminUserService adminUserService) {
		super();
		this.adminUserService = adminUserService;
	}
	

	private AdminUserDetailsServiceImpl userDetailsService;

    @Autowired
	public void setAdminUserDetailsService(AdminUserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	/**
	 * 自定义登录校验
	 * 密码加密
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("使用重写的自定义配置方法 configure(HttpSecurity)。将覆盖父类中的配置方法。");

		http.authorizeRequests()
				// 访问权限控制

				// 站点根路径 '/'，允许所有访问权限
				.antMatchers("/").permitAll()
				// 路径'/admin' 需要授权
				.antMatchers("/admin/**").authenticated().anyRequest().permitAll().and()
				// 指定登录页
				.formLogin().loginPage("/tosignin")
				// 默认登录成功跳转地址
				.defaultSuccessUrl("/admin/dashboard", true).permitAll().and()
				// 指定注销成功跳转地址
				.logout().logoutSuccessUrl("/tosignin").permitAll().and()
				// 端口映射-80 > 443
				.portMapper().http(80).mapsTo(443).and()
				// 禁用 CSRF
				.csrf().disable();

		// 记住我
		http.rememberMe().alwaysRemember(true);

		// 添加第三方登录认证过滤器
		// QQ & Github
		http.addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAt(githubAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * QQ （登录）认证过滤器
	 */
	private QQAuthenticationFilter qqAuthenticationFilter() {

		SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
		successHandler.setAlwaysUseDefaultTargetUrl(true);
		successHandler.setDefaultTargetUrl("/admin/dashboard");

		// qqAuthenticationFilter
		QQAuthenticationFilter qqaf = new QQAuthenticationFilter("/signin/qq");
		qqaf.setAuthenticationManager(new QQAuthenticationManager(adminUserService));
		qqaf.setAuthenticationSuccessHandler(successHandler);

		return qqaf;
	}

	/**
	 * Github （登录）认证过滤器
	 */
	private GithubAuthenticationFilter githubAuthenticationFilter() {

		SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
		successHandler.setAlwaysUseDefaultTargetUrl(true);
		successHandler.setDefaultTargetUrl("/admin/dashboard");

		// githubAuthenticationFilter
		GithubAuthenticationFilter ghaf = new GithubAuthenticationFilter("/signin/github");
		ghaf.setAuthenticationManager(new GithubAuthenticationManager(adminUserService));
		ghaf.setAuthenticationSuccessHandler(successHandler);

		return ghaf;
	}
	
	/**
	 * 获取加密算法对象
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
