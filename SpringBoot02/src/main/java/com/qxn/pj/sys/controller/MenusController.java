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
import com.qxn.pj.sys.entity.Menus;
import com.qxn.pj.sys.service.MenusService;
//Controller+responseBody
@RestController
@RequestMapping("/menus/")
public class MenusController {
	@Autowired
	private MenusService menusService;
	@PutMapping("doUpdateObject")
	public JsonResult doUpdateObject(
			@RequestBody Menus entity) {
		int row = menusService.updateObject(entity);
		return new JsonResult("update ok");
	}
	@PostMapping("doInsertObject")
	public JsonResult doInsertObject(
			@RequestBody Menus entity) {
		menusService.insertObject(entity);
		return new JsonResult("insert ok");
	}
	@DeleteMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		menusService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	@GetMapping("doFindObjects")
	public JsonResult doFindObjects() {
		List<Map<String, Object>> list = menusService.findObjects();
		return new JsonResult(list);
	}
}
