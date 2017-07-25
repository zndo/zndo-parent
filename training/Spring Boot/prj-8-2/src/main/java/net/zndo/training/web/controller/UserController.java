package net.zndo.training.web.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping("/user")
	public String user(@AuthenticationPrincipal Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "user/user";
	}
}
