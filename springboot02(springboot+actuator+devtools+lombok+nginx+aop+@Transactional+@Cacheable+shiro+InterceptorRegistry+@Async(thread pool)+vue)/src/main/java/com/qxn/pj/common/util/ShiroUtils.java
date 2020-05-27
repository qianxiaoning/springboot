package com.qxn.pj.common.util;

import org.apache.shiro.SecurityUtils;

import com.qxn.pj.sys.entity.Users;

public class ShiroUtils {
	public static Users getUser() {
		Users user = 
		(Users)SecurityUtils.getSubject().getPrincipal();
		return user;
	}
}
