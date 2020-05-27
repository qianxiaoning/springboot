package com.qxn.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pj.common.vo.CheckBox;
import com.qxn.pj.common.vo.JsonResult;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Roles;
import com.qxn.pj.sys.service.RolesService;
import com.qxn.pj.sys.vo.RolesMenus;
@RestController
@RequestMapping("/roles/")
public class RolesController {
	@Autowired
	private RolesService rolesService;
	@GetMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name,
			Integer pageCurrent) {
		PageObject<Roles> pageObject = 
		rolesService.findPageObjects(name,pageCurrent);
		return new JsonResult(pageObject);
	}
	@DeleteMapping("doDeleteObject")
	public JsonResult doDeleteObject(
			Integer id) {
		int rows = rolesService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	@PostMapping("doInsertObject")	
	public JsonResult doInsertObject( 
			@RequestBody RolesMenus rolesMenus) {
		int row = rolesService.insertObject(
				rolesMenus.getRoles(),
				rolesMenus.getMenusIds());
		return new JsonResult("insert ok");
	}
	@GetMapping("selectMenusIdsByRoleId")
	public JsonResult doselectMenusIdsByRoleId( 
			Integer rolesId) {
		Integer[] menusIds = rolesService.selectMenusIdsByRoleId(rolesId);
		return new JsonResult(menusIds);
	}
	@PutMapping("doUpdateObject")
	public JsonResult doUpdateObject( 
			@RequestBody RolesMenus rolesMenus) {
		int row = rolesService.updateObject(
				rolesMenus.getRoles(),
				rolesMenus.getMenusIds());
		return new JsonResult("update ok");
	}
	//纯属试验数据层resultMap查两表的用法
	@GetMapping("doFindRolesMenusByRolesId")
	public JsonResult doFindRolesMenusByRolesId(
			Integer id) {
		RolesMenus rolesMenus = 
				rolesService.findRolesMenusByRolesId(id);
		return new JsonResult(rolesMenus);
	}
	//查找所有的角色的checkbox对象
	@GetMapping("doGetRolesCheckBox")
	public JsonResult doGetRolesCheckBox() {
		List<CheckBox> roleList = rolesService.GetRolesCheckBox();
		return new JsonResult(roleList);
	}
	
}
