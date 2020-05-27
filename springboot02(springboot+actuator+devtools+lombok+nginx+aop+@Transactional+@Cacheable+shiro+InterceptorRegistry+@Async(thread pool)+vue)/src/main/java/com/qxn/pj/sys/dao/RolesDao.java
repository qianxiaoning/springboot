package com.qxn.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qxn.pj.common.vo.CheckBox;
import com.qxn.pj.sys.entity.Depts;
import com.qxn.pj.sys.entity.Roles;
import com.qxn.pj.sys.vo.RolesMenus;

@Mapper
public interface RolesDao {
	int insertObject(Roles entity);
	int getRowCount(String name);
	
	List<Roles> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	@Delete("delete from roles where id=#{id}")
	int deleteObject(Integer id);
	int updateObject(Roles roles);
	
	RolesMenus findRolesMenusByRolesId(Integer rolesId);
	@Select("select id,name from roles")
	List<CheckBox> getRolesCheckBox();
}
