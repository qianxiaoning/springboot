package com.qxn.pj.sys.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import lombok.Data;
@Data
public class Users implements Serializable{
	private static final long serialVersionUID = 1916229535898756044L;
	private Integer id;
	private String username;
	private String password;
	//盐值，给密码加密
	private String salt;
	private String email;
	private String mobile;
	//禁用启用状态默认1，启用
	private Integer valid = 1;
	private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
	
	public String toStringValues() {
        StringBuilder result = new StringBuilder("[");
        for (Field declaredField : Users.class.getDeclaredFields()) {
            try {
            	String name = declaredField.getName();
            	Object value = declaredField.get(Users.this);
            	if(name!="serialVersionUID"&&value!=null) {
            		result           
            		.append(name)
            		.append("=")
            		.append(value)
            		.append(",");
            	}
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //去最后一个逗号
        return result.substring(0, result.length() - 1) + "]";
    }
}
