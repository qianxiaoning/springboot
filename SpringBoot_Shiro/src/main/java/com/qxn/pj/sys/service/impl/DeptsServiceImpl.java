package com.qxn.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qxn.pj.common.exception.ServiceException;
import com.qxn.pj.sys.dao.DeptsDao;
import com.qxn.pj.sys.entity.Depts;
import com.qxn.pj.sys.service.DeptsService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class DeptsServiceImpl implements DeptsService{
	@Autowired
	private DeptsDao deptsDao;
	@Override
	public List<Map<String, Object>> findObjects() {		
		List<Map<String, Object>> list = deptsDao.findObjects();
		if(list==null||list.size()==0) {
			throw new ServiceException("没有数据");
		}
		return list;
	}

	@Override
	public int insertObject(Depts depts) {
		if(depts==null) {
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(depts.getName())) {
			throw new IllegalArgumentException("部门名不能为空");
		}
		//...
		int rows = deptsDao.insertObject(depts);		
		return rows;
	}

	@Override
	public int updateObject(Depts depts) {
		if(depts==null) {
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(depts.getName())) {
			throw new IllegalArgumentException("部门名不能为空");
		}
		//...
		int rows;
		try {
			rows = deptsDao.updateObject(depts);
		} catch (Exception e) {
			throw new ServiceException("更新失败");
		}
		return rows;
	}

	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1) {
			throw new IllegalArgumentException("id值无效");
		}
		int count = deptsDao.getChildCount(id);
		if(count>0) {
			throw new ServiceException("有子元素不能删除");
		}
		int rows = deptsDao.deleteObject(id);
		if(rows==0) {
			throw new ServiceException("数据不存在");
		}
		return rows;
	}
}
