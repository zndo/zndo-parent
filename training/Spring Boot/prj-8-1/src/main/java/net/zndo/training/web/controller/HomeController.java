package net.zndo.training.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({"/", "/index" ,"/home"})
	public String root() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
