package com.qxn.pj.sys.service;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Users;
import com.qxn.pj.sys.vo.UserDeptRoleIds;
import com.qxn.pj.sys.vo.UserDeptRolesPra;

public interface UsersService {
	//mybatisAssociationPra练习
	UserDeptRolesPra findUserDeptRolesById(Integer userId);

	UserDeptRoleIds findUserDeptRoleIdsById(Integer userId);

	int insertUser(UserDeptRoleIds userDeptRoleIds);

	PageObject<UserDeptRoleIds> findPageObject(String username, Integer pageCurrent);

	int updateValidByUserId(Users users);

	int updateUserDeptRoleIdsById(UserDeptRoleIds userDeptRoleIds);

}
