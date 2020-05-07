package com.qxn.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	//登录页
	@RequestMapping("")
	public String Login() {
		return "login";
	}
}
