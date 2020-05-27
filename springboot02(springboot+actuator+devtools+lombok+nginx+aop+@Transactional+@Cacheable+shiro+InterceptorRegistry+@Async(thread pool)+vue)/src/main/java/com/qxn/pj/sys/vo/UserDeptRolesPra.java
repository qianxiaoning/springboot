package com.qxn.pj.sys.vo;

import java.io.Serializable;

import com.qxn.pj.sys.entity.Depts;
import com.qxn.pj.sys.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDeptRolesPra implements Serializable{	
	private static final long serialVersionUID = 4692782684535311113L;
	private Users users;
	private Depts depts;
	private Integer[] rolesIds;	
}
