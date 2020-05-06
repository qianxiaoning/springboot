package com.qxn.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class Depts implements Serializable{
	private static final long serialVersionUID = -684444570268008944L;
	
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer sort;
	private String note;
	private String createdUser;
	private String modifiedUser;
	private Date createdTime;
	private Date modifiedTime;
}
