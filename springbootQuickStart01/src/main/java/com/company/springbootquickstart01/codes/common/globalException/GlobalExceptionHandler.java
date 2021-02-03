package com.company.springbootquickstart01.codes.common.globalException;

import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//控制层出现异常，检测controller中@ExceptionHandler注解，
//再检测@ControllerAdvice注解下@ExceptionHandler
//对匹配类型异常处理
//全局异常处理类
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	//异常处理方法，传入异常类型
	@ExceptionHandler(RuntimeException.class)
	public JsonResult doHandleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		log.info(e.getMessage());//错误名
		return new JsonResult(e);
	}
}
