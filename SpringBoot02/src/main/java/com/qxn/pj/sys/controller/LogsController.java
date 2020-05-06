package com.qxn.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pj.common.vo.JsonResult;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.service.LogsService;
@RestController
@RequestMapping("/logs/")
public class LogsController {
	@Autowired
	private LogsService logsService;
	@GetMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String username,Integer pageCurrent) {
		PageObject<Logs> pageObject = logsService.findPageObject(username, pageCurrent);
		return new JsonResult(pageObject);
	}
	@DeleteMapping("deleteObjects")
	public JsonResult deleteObjects(
			Integer ...ids) {
		int counts = logsService.deleteObjects(ids);
		return new JsonResult("删除成功");
	}
	
}
