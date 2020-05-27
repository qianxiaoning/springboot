package com.qxn.pj.sys.vo;

import java.io.Serializable;

import com.qxn.pj.sys.entity.Roles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginVo implements Serializable{	
	private static final long serialVersionUID = -3275779456656841984L;
	private String username;
	private String password;
	private Boolean isRemember;
}
