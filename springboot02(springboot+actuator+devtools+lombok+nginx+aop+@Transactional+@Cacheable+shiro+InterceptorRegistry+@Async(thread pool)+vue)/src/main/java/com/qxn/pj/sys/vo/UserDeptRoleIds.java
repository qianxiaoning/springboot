package com.qxn.pj.sys.vo;

import java.io.Serializable;

import com.qxn.pj.sys.entity.Depts;
import com.qxn.pj.sys.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDeptRoleIds extends Users implements Serializable{	
	private static final long serialVersionUID = -7717873489946687989L;
	private Depts depts;
	private Integer[] roleIds;	
}
