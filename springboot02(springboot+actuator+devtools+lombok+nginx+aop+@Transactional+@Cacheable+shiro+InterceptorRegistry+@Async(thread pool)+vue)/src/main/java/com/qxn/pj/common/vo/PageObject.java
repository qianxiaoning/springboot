package com.qxn.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable{	
	private static final long serialVersionUID = -828820889273298055L;
	private Integer pageCurrent=1;
	private Integer pageSize=3;
	private Integer rowCount=0;
	//总页数（计算获得）
	private Integer pageCount=0;
	//当前页记录
	private List<T> records;
	public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
		super();
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		this.pageCount=(rowCount-1)/pageSize+1;
	}	
}
