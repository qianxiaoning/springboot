package com.qxn.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qxn.pj.sys.entity.Depts;

@Mapper
public interface DeptsDao {
	@Select("select c.*,p.name parentName from "
			+ "depts c left join depts p on "
			+ "c.parentId=p.id")
	List<Map<String, Object>> findObjects();
	int insertObject(Depts depts);

	int updateObject(Depts depts);
	@Delete("delete from depts where id=#{id}")
	int deleteObject(Integer id);
	@Select("select count(*) from depts where parentId=#{id}")
	int getChildCount(Integer id);
	//mybatisAssociationPra练习
	@Select("select * from depts where id=#{deptId}")
	Depts selectDeptById(Integer deptId);
}
