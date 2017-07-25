package net.zndo.training.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import net.zndo.training.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private UserDetailsServiceImpl userDetailsService;
	
	public UserDetailsServiceImpl getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * 用户/角色配置
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/**
	 * Web 权限验证配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/")// 认证请求匹配 "/" 时，允许所有访问
				.permitAll().antMatchers("/user/**").hasRole("USER") // 认证请求匹配 "/user" 开始的请求，仅允许具有 "USER" 角色访问
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/user")// 表单登录的登录页路径，和默认成功跳转页面
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout");// 注销页路径和注销成功页面

	}
}
