package net.zndo.training.prj41.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/log4j2")
	public String log4j2Demo() {
		return "成功。";
	}

}
