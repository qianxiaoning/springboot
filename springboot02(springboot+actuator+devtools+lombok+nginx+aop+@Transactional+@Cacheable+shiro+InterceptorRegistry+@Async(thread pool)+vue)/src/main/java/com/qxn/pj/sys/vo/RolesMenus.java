package com.qxn.pj.sys.vo;

import java.io.Serializable;

import com.qxn.pj.sys.entity.Roles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolesMenus implements Serializable{	
	private static final long serialVersionUID = -8042095643912018424L;
	private Roles roles;
	private Integer[] menusIds;	
}
