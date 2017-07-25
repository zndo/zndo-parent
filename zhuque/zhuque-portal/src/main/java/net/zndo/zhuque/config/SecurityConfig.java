package net.zndo.zhuque.config;

import net.zndo.zhuque.common.security.ZhuqueUserDetailService;
import net.zndo.zhuque.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.client.OAuth2RestOperations;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZhuqueUserDetailService zhuqueUserDetailService;

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/token", "/rest/**", "/api/**", "/**")
                .and()
                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        if (isProfileActive("test")) {
            inMemoryConfigurer().withUser("testaccount").password("testaccount").authorities("ROLE_TEST").and().configure(auth);
        }

        auth.authenticationProvider(daoAuthenticationProvider);
    }


    private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryConfigurer() {
        return new InMemoryUserDetailsManagerConfigurer<>();
    }


    //    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(mzUserDetailService).passwordEncoder(EncoderUtil.pwdEncoder);
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(zhuqueUserDetailService);
        impl.setPasswordEncoder(EncoderUtil.pwdEncoder);
        impl.setHideUserNotFoundExceptions(false) ;
        return impl ;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 判断参数里的Profile是否在激活的Profile里, 推荐使用ApplicationContextRegister里的方法
     * Note: 在不设置任何Profile时, ActiveProfile会为空, 但是实际上会使用DefaultProfile
     * @return
     */
    @Deprecated
    private Boolean isProfileActive (String testProfile) {

        if(environment.getActiveProfiles().length > 0) {
            return Arrays.asList(environment.getActiveProfiles()).stream()
                    .filter(x -> x.equalsIgnoreCase(testProfile)).findFirst().isPresent();
        }

        else if(environment.getDefaultProfiles().length > 0) {
            return Arrays.asList(environment.getDefaultProfiles()).stream()
                    .filter(x -> x.equalsIgnoreCase(testProfile)).findFirst().isPresent();
        }
        else {
            return false;
        }
    }

}
