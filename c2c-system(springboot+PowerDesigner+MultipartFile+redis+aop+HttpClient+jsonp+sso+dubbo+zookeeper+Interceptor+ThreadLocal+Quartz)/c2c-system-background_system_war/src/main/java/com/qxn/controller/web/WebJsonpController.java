package com.qxn.controller.web;

import com.qxn.vo.SysResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qxn.pojo.ProductDesc;
import com.qxn.util.ObjectMapperUtil;
/**
 *要求返回值结果callback(json)
 */
@RestController
@RequestMapping("/web")
public class WebJsonpController {
	@RequestMapping("/testJSONPObject")
	//JSONPObject将参数传入，不用自己拼接字符串
	public JSONPObject testJsonpObject(String callback) {
		ProductDesc ProductDesc = new ProductDesc();
		ProductDesc.setItemId(100L)
				.setItemDesc("跨域测试");
		SysResult result = SysResult.success(ProductDesc);
		String json = ObjectMapperUtil.toJSON(result);
		return new JSONPObject(callback, json);
	}
	@GetMapping("/testJsonp")
	public String testJsonp(String callback) {
		ProductDesc ProductDesc = new ProductDesc();
		ProductDesc.setItemId(100l)
					.setItemDesc("jsonp测试");
		//对象转化json
		SysResult result = SysResult.success(ProductDesc);
		String json = ObjectMapperUtil.toJSON(result);
		return callback+"("+json+")";
	}
}
