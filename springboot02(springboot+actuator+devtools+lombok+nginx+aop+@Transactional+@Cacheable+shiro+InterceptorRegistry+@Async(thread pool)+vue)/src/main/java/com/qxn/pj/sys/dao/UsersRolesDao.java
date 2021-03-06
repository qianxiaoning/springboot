package com.qxn.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qxn.pj.sys.vo.UserDeptRoleIds;

@Mapper
public interface UsersRolesDao {
	@Delete("delete from users_roles where role_id=#{id}")
	int deleteObjectByRoleId(Integer id);
	//mybatisAssociationPra练习</if>
	@Select("select role_id from users_roles where user_id=#{id}")
	List<Integer> findRoleIdsByUsersId(Integer id);
	int insertRoles(UserDeptRoleIds userDeptRoleIds);
	@Delete("delete from users_roles where user_id=#{usersId}")
	int deleteObjectsByUserId(int usersId);
}
