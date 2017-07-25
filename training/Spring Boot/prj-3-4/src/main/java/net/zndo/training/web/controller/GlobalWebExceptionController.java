package net.zndo.training.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.zndo.training.exceptions.UdException;

@RestController
public class GlobalWebExceptionController {

	@RequestMapping(value="/exdemo", produces = "application/json; charset=utf-8")
	public String exdemo() throws Exception{
		throw new Exception("异常");
	}
	
	@RequestMapping(value="/json", produces = "application/json; charset=utf-8")
	public String udExceptionDemo() throws UdException{
		throw new UdException("发生自定义异常");
	}
	
}
