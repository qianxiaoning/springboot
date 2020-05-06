package com.qxn.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pj.common.vo.JsonResult;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.entity.Users;
import com.qxn.pj.sys.service.UsersService;
import com.qxn.pj.sys.vo.UserDeptRoleIds;
import com.qxn.pj.sys.vo.UserDeptRolesPra;
@RestController
@RequestMapping("/users/")
public class UsersController {
	@Autowired
	private UsersService usersService;
	//mybatisAssociationPra练习
	@GetMapping("doGetUserDeptRolesByUserId")
	public JsonResult doGetUserDeptMenusByUserId(
			Integer userId) {
		UserDeptRolesPra userDeptRolesPra = usersService.findUserDeptRolesById(userId);
		return new JsonResult(userDeptRolesPra);
	}	
	@GetMapping("doGetUserDeptRoleIdsByUserId")
	public JsonResult doGetUserDeptRoleIdsByUserId(
			Integer userId) {
		UserDeptRoleIds userDeptRoleIds = usersService.findUserDeptRoleIdsById(userId);
		return new JsonResult(userDeptRoleIds);
	}	
	@PostMapping("doInsertUser")
	public JsonResult doInsertUser(@RequestBody 
			UserDeptRoleIds userDeptRoleIds) {		
		int row = usersService.insertUser(userDeptRoleIds);
		return new JsonResult("insert ok");
	}	
	@GetMapping("doFindPageUsers")
	public JsonResult doFindPageUsers(
			String username,Integer pageCurrent) {
		PageObject<UserDeptRoleIds> pageObject = usersService.findPageObject(username, pageCurrent);
		return new JsonResult(pageObject);
	}
	@PutMapping("updateValidByUserId")
	public JsonResult updateValidByUserId(@RequestBody
			Users users) {
		int rows = usersService.updateValidByUserId(users);
		return new JsonResult("状态修改成功");
	}
	@PutMapping("updateUserDeptRoleIdsById")
	public JsonResult updateUserDeptRoleIdsById(@RequestBody
			UserDeptRoleIds userDeptRoleIds) {
		int rows = usersService.updateUserDeptRoleIdsById(userDeptRoleIds);
		return new JsonResult("修改成功");
	}
	
}
