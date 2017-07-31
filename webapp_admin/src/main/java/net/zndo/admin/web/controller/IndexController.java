package net.zndo.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import net.zndo.admin.data.entity.AdminUser;
import net.zndo.admin.service.IAdminUserService;

/**
 * 首页控制器
 * @author zndo
 *
 */
@Controller
@AllArgsConstructor
public class IndexController {
	
	private IAdminUserService adminUserService;

	// 默认页
	@GetMapping("/")
	public String toRoot(){
			return "page_user_login_1";
	}
	
	// 跳转到注册页
    @GetMapping("/tosignup")
    public String toSignup(){
        return "page_user_login_1";
    }

	// 注册
    @PostMapping("/signup")
    public String doSignup(AdminUser adminUser){
        if (adminUserService.addAdminUser(adminUser)) {
            return "redirect:tosignup?success";
        }
        return "redirect:tosignup?error";
    }
	
	// 跳转到登录页
	@GetMapping("/tosignin")
	public String toSignin(){
			return "page_user_login_1";
	}
	
	// 登录
	@GetMapping("/signin")
	public String doSignin(){
			return "page_user_login_1";
	}
	
	// 注销
	@GetMapping("/signout")
	public String signout(){
		return "page_user_login_1";
	}
	
}
