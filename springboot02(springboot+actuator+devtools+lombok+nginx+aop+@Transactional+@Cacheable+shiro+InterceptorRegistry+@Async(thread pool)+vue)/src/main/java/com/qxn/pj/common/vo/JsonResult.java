package com.qxn.pj.common.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult implements Serializable{	
	private static final long serialVersionUID = -3591920633869336139L;
	//状态码：200成功，302token过期，
	//403请求无效
	private int code=200;
	//信息
	private String message="ok";
	//数据
	private Object data;
	//构造
	public JsonResult(String messaeg) {
		this.message = messaeg;
	}
	public JsonResult(Object data) {
		this.data = data;
	}
	public JsonResult(int code,String messaeg) {
		this.code = code;
		this.message = messaeg;
	}
	public JsonResult(Throwable e) {
		this.code = 403;
		this.message=e.getMessage();
	}
}
