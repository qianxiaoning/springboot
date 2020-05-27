package com.qxn.pj.sys.service;
import java.util.List;

import com.qxn.pj.common.vo.CheckBox;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Roles;
import com.qxn.pj.sys.vo.RolesMenus;

public interface RolesService {

	PageObject<Roles> findPageObjects(String name, Integer pageCurrent);

	int deleteObject(Integer id);
	
	int insertObject(Roles entity,Integer[] menusIds);
	//或者

	Integer[] selectMenusIdsByRoleId(Integer rolesId);

	int updateObject(Roles roles, Integer[] menusIds);
	
	RolesMenus findRolesMenusByRolesId(Integer rolesId);

	List<CheckBox> GetRolesCheckBox();
}
