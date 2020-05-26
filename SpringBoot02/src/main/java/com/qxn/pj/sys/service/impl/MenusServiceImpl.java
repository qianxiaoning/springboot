package com.qxn.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.qxn.pj.common.annotation.RequiredLog;
import com.qxn.pj.common.exception.ServiceException;
import com.qxn.pj.sys.dao.MenusDao;
import com.qxn.pj.sys.dao.RolesMenusDao;
import com.qxn.pj.sys.entity.Menus;
import com.qxn.pj.sys.service.MenusService;

import lombok.extern.slf4j.Slf4j;
@Transactional(readOnly = false,
isolation = Isolation.READ_COMMITTED
,rollbackFor = Throwable.class,timeout = 30)
@Service
@Slf4j
public class MenusServiceImpl implements MenusService{
	@Autowired
	private MenusDao menusDao;
	@Autowired
	private RolesMenusDao rolesMenusDao;
	//log和事务2个切面
	@RequiredLog("查询菜单")
	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> findObjects() {
		//看当前线程名称
		String tName = Thread.currentThread().getName();
		System.out.println("findMenus.thread.name = " + tName);
		List<Map<String, Object>> list = menusDao.findObjects();
		if(list.size()==0)
			throw new ServiceException("no records");
		return list;
	}
	@Transactional
	@RequiredLog
	@Override
	public int deleteObject(Integer id) {
		//1.对id校验
		if(id==null||id<1) {
			throw new IllegalArgumentException("id值无效");
		}
		//2.看是否有子菜单
		int counts = menusDao.getChildCount(id);
		//3.删除角色菜单关联表
		if(counts>0) {
			//有子菜单不能删除，属于业务异常，抛出业务异常
			throw new ServiceException("请先删除子菜单");
		}
		rolesMenusDao.deleteObjectsByMenusId(id);
		//4.删除菜单表
		int rows = menusDao.deleteObject(id);
		if(rows==0) {
			throw new ServiceException("菜单已经不存在");
		}		
		//5.返回结果
		return rows;
	}
	@Override
	public int insertObject(Menus entity) {
		if(entity==null)throw new 
		IllegalArgumentException("保存对象不能为空");
		//spring中的工具类
		if(StringUtils.isEmpty(entity.getName()))throw new 
		IllegalArgumentException("菜单名不能为空");
		//...剩下参数校验
		//tomcat => mysql，如果mysql服务器断网了会异常
		//捕获异常
		int rows = 0;
		try {
			rows = menusDao.insertObject(entity);
		} catch (Throwable e) {
			log.error(e.getMessage());
			//send message to 运维人员
			//...
			//报警  -aop
			//Runtime.getRuntime().exec(command);
			throw new ServiceException("系统维护中");
		}
		return rows;
	}
	@Override
	public int updateObject(Menus entity) {
		if(entity==null)throw new 
		IllegalArgumentException("保存对象不能为空");
		//spring中的工具类
		if(StringUtils.isEmpty(entity.getName()))throw new 
		IllegalArgumentException("菜单名不能为空");
		//...剩下参数校验
		//tomcat => mysql，如果mysql服务器断网了会异常
		//捕获异常
		int rows = 0;
		try {
			rows = menusDao.updateObject(entity);
		} catch (Throwable e) {
			log.error(e.getMessage());
			//send message to 运维人员
			//...
			//报警  -aop
			//Runtime.getRuntime().exec(command);
			throw new ServiceException("系统维护中");
		}
		return rows;
	}
}
