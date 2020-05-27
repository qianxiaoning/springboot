package com.qxn.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qxn.pj.sys.entity.Logs;

//启动类未加@MapperScan，则在此加@Mapper
@Mapper
public interface LogsDao {
	//根据条件查询记录总数
	int getRowCount(@Param("username")String username);
	//查询当前页	
	List<Logs> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	//删除
	int deleteObjects(@Param("ids")Integer ...ids);
	
	int insertLog(Logs entity);
}
