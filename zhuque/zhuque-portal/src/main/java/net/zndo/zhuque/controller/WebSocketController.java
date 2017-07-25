package net.zndo.zhuque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebSocketController {

	
	@RequestMapping("/socket")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("socket");
		return mv;
	}
}
