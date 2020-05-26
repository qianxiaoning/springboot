package com.qxn.pj.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pj.common.util.ShiroUtils;
import com.qxn.pj.common.vo.JsonResult;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Users;
import com.qxn.pj.sys.service.UsersService;
import com.qxn.pj.sys.vo.EditPassword;
import com.qxn.pj.sys.vo.LoginVo;
import com.qxn.pj.sys.vo.UserDeptRoleIds;
import com.qxn.pj.sys.vo.UserDeptRolesPra;
@RestController
@RequestMapping("/users/")
public class UsersController {
	@Autowired
	private UsersService usersService;
	@GetMapping("getLoginUser")
	public JsonResult getLoginUser() {
		Users user = ShiroUtils.getUser();
		return  new JsonResult(user);
	}
	@PutMapping("editPassword")
	public JsonResult editPassword(
			@RequestBody EditPassword editPassword) {
		int rows = usersService.editPassword(editPassword);
		return new JsonResult("密码修改成功");
	}
	//返回登录页面
	@GetMapping("returnToLogin")
	public JsonResult returnToLogin() {
		String msg = "登录失效";
		return new JsonResult(302,msg);
	}
	@PostMapping("doLogin")
	public JsonResult doLogin(@RequestBody LoginVo loginVo) {
		usersService.doLogin(loginVo);		
		return new JsonResult("登录成功");
	}

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
		Users user = (Users)SecurityUtils.getSubject().getPrincipal();
		users.setModifiedUser(user.getUsername());
		System.out.println(users);
		int rows = usersService.updateValidByUserId(users);
		System.out.println(users.getValid());
		return new JsonResult("状态修改成功");
	}
	@PutMapping("updateUserDeptRoleIdsById")
	public JsonResult updateUserDeptRoleIdsById(@RequestBody
			UserDeptRoleIds userDeptRoleIds) {
		int rows = usersService.updateUserDeptRoleIdsById(userDeptRoleIds);
		return new JsonResult("修改成功");
	}
	
}
