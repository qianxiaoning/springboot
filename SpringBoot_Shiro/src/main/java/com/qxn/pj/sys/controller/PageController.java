package com.qxn.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	//首页
	@RequestMapping("index")
	public String Index() {
		return "index";
	}
	//登录页
	@RequestMapping("login")
	public String Login() {
		return "login";
	}
}
