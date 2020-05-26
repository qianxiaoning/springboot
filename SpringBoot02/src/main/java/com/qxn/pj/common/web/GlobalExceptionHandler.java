package com.qxn.pj.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qxn.pj.common.vo.JsonResult;
//控制层出现异常，检测controller中@ExceptionHandler注解，
//再检测@ControllerAdvice注解下@ExceptionHandler
//对匹配类型异常处理
//全局异常处理类
@RestControllerAdvice
public class GlobalExceptionHandler {
	//异常处理方法，传入异常类型
	@ExceptionHandler(RuntimeException.class)
	public JsonResult doHandleRuntimeException(
			RuntimeException e) {
		e.printStackTrace();
		return new JsonResult(e);
	}
	//匹配shiro的异常
	@ExceptionHandler(ShiroException.class)
	public JsonResult doHandleShiroException(
			ShiroException e) {
		JsonResult result = new JsonResult();
		result.setCode(403);
		if(e instanceof UnknownAccountException) {
			result.setMessage("账号不存在");
//			result.setMessage("用户或密码不正确");
		}else if(e instanceof LockedAccountException) {
			result.setMessage("账号被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			result.setMessage("密码不正确");
//			result.setMessage("用户或密码不正确");
		}else if(e instanceof AuthorizationException) {
			result.setMessage("没有操作权限");
		}else {
			result.setMessage("系统维护中");
		}
		e.printStackTrace();
		return result;
	}
}
