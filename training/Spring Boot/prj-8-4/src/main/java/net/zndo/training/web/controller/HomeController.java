package net.zndo.training.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.zndo.training.data.entity.User;
import net.zndo.training.service.IUserService;

@Controller
public class HomeController {
	
	@Autowired
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
        if (userService.insert(user))
            return "redirect:register?success";
        return "redirect:register?error";
    }


}
