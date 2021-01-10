package com.company.springbootquickstart01.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class JsonResult<T> implements Serializable{
	private static final long serialVersionUID = -3591920633869336139L;
	//状态码：200成功，302token过期，
	//403请求无效
	private Integer code=200;
	//信息
	private String message="ok";
	//数据
	private T data;
	//构造
	public JsonResult(String messaeg) {
		this.message = messaeg;
	}
	public JsonResult(T data) {
		this.data = data;
	}
	public JsonResult(int code, String messaeg) {
		this.code = code;
		this.message = messaeg;
	}
	public JsonResult(Throwable e) {
		this.code = 403;
		this.message=e.getMessage();
	}
}
