package com.qxn.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qxn.pj.sys.entity.Users;
import com.qxn.pj.sys.vo.UserDeptRoleIds;
import com.qxn.pj.sys.vo.UserDeptRolesPra;


@Mapper
public interface UsersDao {
	//mybatisAssociationPra练习
	UserDeptRolesPra findUserDeptRolesById(Integer userId);

	UserDeptRoleIds findUserDeptRoleIdsById(Integer userId);

	int insertUser(UserDeptRoleIds userDeptRoleIds);
	
	int getRowCount(String username);

	List<UserDeptRoleIds> findPageObjects(String username, int startIndex, int pageSize);
	@Update("update users set valid=#{valid},modifiedTime=now(),modifiedUser=#{modifiedUser} where id=#{id}")
	int updateValidByUserId(Users users);

	int updateInfo(UserDeptRoleIds userDeptRoleIds);
	@Select("select * from users where username=#{username}")
	Users findUsersByUserName(String username);
	
	int editPassword(Users user);
}
