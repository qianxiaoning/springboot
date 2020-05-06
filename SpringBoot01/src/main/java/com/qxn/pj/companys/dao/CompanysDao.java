package com.qxn.pj.companys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
//表示此接口在底层产生一个实现类对象
@Mapper
public interface CompanysDao {
	@Delete("delete from game_company where id=#{id}")
	int deleteObject(Integer id);
	
	int deleteObjects(Integer ...ids);
}
