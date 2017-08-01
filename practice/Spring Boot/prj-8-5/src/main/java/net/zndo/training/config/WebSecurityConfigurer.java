package net.zndo.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.zndo.training.service.impl.UserDetailsServiceImpl;
import net.zndo.training.web.filters.QQAuthenticationFilter;
import net.zndo.training.web.filters.QQAuthenticationManager;

@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

//	private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//	public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}
	
//	/**
//	 * 用户/角色配置
//	 * 
//	 * @param auth
//	 * @throws Exception
//	 */
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

	/**
	 * Web 权限验证配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/")// 认证请求匹配 "/" 时，允许所有访问
				.permitAll()
			.antMatchers("/user/**").hasRole("USER") // 认证请求匹配 "/user" 开始的请求，仅允许具有 "USER" 角色访问
				.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/user")// 表单登录的登录页路径，和默认成功跳转页面
				.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/logout");// 注销页路径和注销成功页面

        	http.addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

    /**
     * 自定义 QQ登录 过滤器
     */
    private QQAuthenticationFilter qqAuthenticationFilter(){
        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/login/qq");
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setAlwaysUseDefaultTargetUrl(true);
        successHandler.setDefaultTargetUrl("/user");
        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        return authenticationFilter;
    }
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
