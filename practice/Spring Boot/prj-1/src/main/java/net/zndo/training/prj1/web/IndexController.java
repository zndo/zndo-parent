package net.zndo.training.prj1.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@RequestMapping("/index")
	public String index(){
		return "你好，这是首页内容。";
	}
}
