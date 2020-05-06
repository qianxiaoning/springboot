package com.qxn.pj.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxn.pj.common.vo.JsonResult;
//控制层出现异常，检测controller中@ExceptionHandler注解，
//再检测@ControllerAdvice注解下@ExceptionHandler
//对匹配类型异常处理
//全局异常处理类
@ControllerAdvice
public class GlobalExceptionHandler {
	//异常处理方法，传入异常类型
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException(
			RuntimeException e) {
		e.printStackTrace();
		return new JsonResult(e);
	}
}
