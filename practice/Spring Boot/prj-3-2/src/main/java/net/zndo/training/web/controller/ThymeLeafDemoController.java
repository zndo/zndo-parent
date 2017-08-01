package net.zndo.training.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThymeLeafDemoController {

	@RequestMapping("/demo")
	@ResponseBody
	public String demo() {
		return "ThymeLeaf Demo";
	}
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("host", "http://zndo.live");
		return mv;
	}
}
