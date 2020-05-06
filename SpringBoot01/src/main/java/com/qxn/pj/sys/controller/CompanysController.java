package com.qxn.pj.sys.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxn.pj.companys.pojo.Companys;

@Controller
@RequestMapping("/companys/")
public class CompanysController {	
	//传入当前类名字
	private static final Logger log = 
	LoggerFactory.getLogger(CompanysController.class);
	@RequestMapping("doDeleteCompanys")
	@ResponseBody
	public String doDeleteCompanys(Integer ...ids) {
		//执行删除操作
		return "delete objects ids "+Arrays.toString(ids);
	}
	@RequestMapping("doCompanysUI")
	public String doCompanysUI() {
		//输出日志
		log.info("request companys.html");
		return "companys";
	}
}
