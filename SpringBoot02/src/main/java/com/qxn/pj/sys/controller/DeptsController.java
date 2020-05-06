package com.qxn.pj.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping("doFindObjects")
	public JsonResult doFindObjects() {
		List<Map<String, Object>> list = deptsService.findObjects();
		return new JsonResult(list);
	}
	@PostMapping("doInsertObject")
	public JsonResult doInsertObject(
			@RequestBody Depts depts) {
		int rows = deptsService.insertObject(depts);
		return new JsonResult("insert ok");
	}
	@PutMapping("doUpdateObject")
	public JsonResult doUpdateObject(
			@RequestBody Depts depts) {
		int rows = deptsService.updateObject(depts);
		return new JsonResult("update ok");
	}
	@DeleteMapping("doDeleteObject")
	public JsonResult doDeleteObject(
			Integer id) {
		int rows = deptsService.deleteObject(id);
		return new JsonResult("delete ok");
	}
}
