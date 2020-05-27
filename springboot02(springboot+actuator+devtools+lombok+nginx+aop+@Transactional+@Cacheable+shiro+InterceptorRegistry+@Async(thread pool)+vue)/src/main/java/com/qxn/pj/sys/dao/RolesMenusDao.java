package com.qxn.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qxn.pj.sys.entity.Roles;

@Mapper
public interface RolesMenusDao {
	//根据menusId删除关联表数据
	@Delete("delete from roles_menus where menus_id=#{menusId}")
	int deleteObjectsByMenusId(Integer menusId);
	@Delete("delete from roles_menus where roles_id=#{id}")
	int deleteObjectByRoleId(Integer id);
	int insertObjects(@Param("rolesId")Integer rolesId,
			@Param("menusIds")Integer[] menusIds);
	@Select("select distinct menus_id from roles_menus where roles_id=#{rolesId}")
	Integer[] selectMenusIdsByRoleId(
			Integer rolesId);
	//基于多个角色id获取菜单id
	List<Integer> selectMenusIdsByRoleIds(
			@Param("rolesIds")Integer[] rolesIds);
	@Delete("delete from roles_menus where roles_id=#{rolesId}")
	int deleteObjects(Integer rolesId);
}
