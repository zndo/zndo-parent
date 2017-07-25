package net.zndo.training.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.zndo.training.data.entity.User;
import net.zndo.training.service.IUserService;

@Controller
public class HomeController {
	
	private IUserService userService;

	@RequestMapping({"/", "/index" ,"/home"})
	public String root() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(User user){
        // 此处省略校验逻辑
        if (userService.insert(user))
            return "redirect:register?success";
        return "redirect:register?error";
    }


}
