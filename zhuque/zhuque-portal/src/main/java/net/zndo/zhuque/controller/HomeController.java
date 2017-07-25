package net.zndo.zhuque.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("home")
	public String home() {

//		new Date().toString();
//		Wed Jun 28 14:13:39 CST 2017

		String logContent = "在 " + new Date().toString() + " 接收到请求";
		logger.warn(logContent);
		return logContent;
	}
}
