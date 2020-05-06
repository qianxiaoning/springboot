package com.qxn.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data//setter,getter,toString
//@NoArgsConstructor
//@AllArgsConstructor
public class Logs implements Serializable{	
	private static final long serialVersionUID = 3157927346217089328L;
	private Integer id;
	private String username;
	private String operation;
	private String method;
	private String params;
	private Long operationTime;
	private String ip;
	private Date createdTime;
	
}
