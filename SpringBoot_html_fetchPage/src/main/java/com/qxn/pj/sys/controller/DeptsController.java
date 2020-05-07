package com.qxn.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pj.common.vo.JsonResult;
import com.qxn.pj.sys.entity.Depts;
import com.qxn.pj.sys.service.DeptsService;
//Controller+responseBody
@RestController
@RequestMapping("/depts/")
public class DeptsController {
	@Autowired
	private DeptsService deptsService;
	@GetMapping("findDepts")
	public JsonResult findDepts() {
		List<Depts> list = deptsService.findDepts();
		return new JsonResult(list);
	}
}
