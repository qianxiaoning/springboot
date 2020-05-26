package com.qxn.pj.companys.pojo;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data//get,set,toString
@Accessors(chain = true)
//@Setter
//@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Companys implements Serializable{
	private static final long serialVersionUID = 85027118431959155L;
	//传入当前类名字，@Slf4j注解代替
//	private static final Logger log = 
//	LoggerFactory.getLogger(Companys.class);
	
	private Integer id;
	private String name;
	private String remark;
	private Date createdTime;
	
	public String doPrint() {
		log.info("companys.display()");
		return toString();
	}
}
