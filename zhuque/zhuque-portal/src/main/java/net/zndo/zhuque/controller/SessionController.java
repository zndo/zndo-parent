package net.zndo.zhuque.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@GetMapping("/session")
	public String session(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (null == uid) {
			uid = UUID.randomUUID();
		}

		session.setAttribute("uid", uid);

		String hostName;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "获取主机名失败";
		}
		return "主机名: " + hostName + "<br/>UUID: " + uid.toString();

	}
}
