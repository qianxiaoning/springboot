package com.qxn.pj.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pj.common.vo.JsonResult;

@RestController
@RequestMapping("/")
public class PageController {	
	@GetMapping("doLogin")
	public JsonResult doLogin() {
		String msg = "登录失效";
		return new JsonResult(msg);
	}
}
