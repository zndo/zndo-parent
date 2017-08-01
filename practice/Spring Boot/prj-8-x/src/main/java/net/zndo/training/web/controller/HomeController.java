package net.zndo.training.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//	@RequestMapping("/")
//	public String root() {
//		return "index";
//	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/allow")
	public String allow() {
		return "allow";
	}
}
