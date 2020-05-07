package com.qxn.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qxn.pj.sys.entity.Logs;

//启动类未加@MapperScan，则在此加@Mapper
@Mapper
public interface LogsDao {
	List<Logs> findLogs();
}
