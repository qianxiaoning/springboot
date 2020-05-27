package com.qxn.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qxn.pj.common.exception.ServiceException;
import com.qxn.pj.common.vo.CheckBox;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.dao.RolesDao;
import com.qxn.pj.sys.dao.RolesMenusDao;
import com.qxn.pj.sys.dao.UsersRolesDao;
import com.qxn.pj.sys.entity.Roles;
import com.qxn.pj.sys.service.RolesService;
import com.qxn.pj.sys.vo.RolesMenus;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class RolesServiceImpl implements RolesService{
	@Autowired
	private RolesDao rolesDao;
	@Autowired
	private RolesMenusDao rolesMenusDao;
	@Autowired
	private UsersRolesDao usersRolesDao;

	@Override
	public PageObject<Roles> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1) {
			throw new IllegalArgumentException("页码无效");
		}
		int rowCount = rolesDao.getRowCount(name);
		if(rowCount==0) {
			throw new ServiceException("无数据");
		}
		int pageSize = 2;
		int startIndex = (pageCurrent-1)*pageSize;
		List<Roles> records = rolesDao.findPageObjects(name, startIndex, pageSize);
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

	@Override
	public int deleteObject(Integer id) {
		//id不存在
		if(id==null||id<1) {
			throw new IllegalArgumentException("id无效");
		}
		rolesMenusDao.deleteObjectByRoleId(id);
		usersRolesDao.deleteObjectByRoleId(id);
		int rows = rolesDao.deleteObject(id);
		if(rows==0) {
			throw new ServiceException("无数据");
		}		
		return rows;
	}

	@Override
	public int insertObject(Roles entity, Integer[] menusIds) {
		if(entity==null)
			throw new IllegalArgumentException(
					"保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName())) {
			throw new IllegalArgumentException(
					"角色名不能为空");
		}
		//判断角色名的唯一性（查询数据库）
		if(menusIds==null||menusIds.length==0) {
			throw new IllegalArgumentException(
					"需要为角色分配菜单权限");
		}
		int row = rolesDao.insertObject(entity);
		rolesMenusDao.insertObjects(entity.getId(),
				menusIds);
		return row;
	}

	@Override
	public Integer[] selectMenusIdsByRoleId(Integer rolesId) {
		if(rolesId==null||rolesId<1) {
			throw new IllegalArgumentException(
					"rolesId无效");
		}
		Integer[] menusIds = rolesMenusDao.selectMenusIdsByRoleId(rolesId);
		return menusIds;
	}

	@Override
	public int updateObject(Roles roles, Integer[] menusIds) {
		if(roles==null)
			throw new IllegalArgumentException(
					"保存对象不能为空");
		if(StringUtils.isEmpty(roles.getId())) {
			throw new IllegalArgumentException(
					"id不能为空");
		}
		if(StringUtils.isEmpty(roles.getName())) {
			throw new IllegalArgumentException(
					"角色名不能为空");
		}
		//判断角色名的唯一性（查询数据库）
		if(menusIds==null||menusIds.length==0) {
			throw new IllegalArgumentException(
					"需要为角色分配菜单权限");
		}
		int row = rolesDao.updateObject(roles);
		//先删原来的关系记录
		rolesMenusDao.deleteObjects(roles.getId());
		//插入新的关系记录
		rolesMenusDao.insertObjects(roles.getId(),
				menusIds);
		return row;
	}

	@Override
	public RolesMenus findRolesMenusByRolesId(Integer rolesId) {
		//1.校验
		if(rolesId==null||rolesId<1) {
			throw new IllegalArgumentException(
					"id无效");
		}
		RolesMenus rolesMenus = rolesDao.findRolesMenusByRolesId(rolesId);
		if(rolesMenus==null) {
			throw new ServiceException("记录已不存在");
		}
		return rolesMenus;
	}

	@Override
	public List<CheckBox> GetRolesCheckBox() {
		List<CheckBox> list = rolesDao.getRolesCheckBox();
		if(list.size()==0) {
			throw new ServiceException("没有角色");
		}
		return list;
	}
}
