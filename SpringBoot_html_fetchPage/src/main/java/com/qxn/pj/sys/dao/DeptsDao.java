package com.qxn.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qxn.pj.sys.entity.Depts;

@Mapper
public interface DeptsDao {	
	List<Depts> findDepts();
}
