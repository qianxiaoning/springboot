package com.qxn.pj.sys.vo;

import java.io.Serializable;

import com.qxn.pj.sys.entity.Roles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditPassword implements Serializable{	
	private static final long serialVersionUID = 5576531239186113798L;
	private String oldPassword;
	private String newPassword;
	private String newConfirm;
}
