package net.zndo.admin.web.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import net.zndo.admin.data.entity.AdminUser;

/**
 * 后台管理用户控制器
 * @author zndo
 *
 */
@Controller
@AllArgsConstructor
public class AdminUserController {

	// 首页
	@GetMapping("/admin/dashboard")
	public String toSignin(){
			return "index";
	}
	
	// 个人中心
	@GetMapping("/admin/user/profile")
	public String profile(@AuthenticationPrincipal UsernamePasswordAuthenticationToken authenticationToken, Model model) {
		AdminUser adminUser = (AdminUser)authenticationToken.getPrincipal();
		model.addAttribute("admin_user", adminUser);
		return "page_user_profile_1";
	}
	
}
