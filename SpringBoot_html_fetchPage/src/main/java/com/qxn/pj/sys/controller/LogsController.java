package com.qxn.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pj.common.vo.JsonResult;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.service.LogsService;
@RestController
@RequestMapping("/logs/")
public class LogsController {
	@Autowired
	private LogsService logsService;
	@GetMapping("findLogs")
	public JsonResult findLogs() {
		List<Logs> list = logsService.findLogs();
		return new JsonResult(list);
	}		
}
