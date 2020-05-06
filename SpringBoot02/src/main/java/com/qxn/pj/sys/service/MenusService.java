package com.qxn.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.qxn.pj.sys.entity.Menus;

public interface MenusService {
	int insertObject(Menus entity);
	int deleteObject(Integer id);
	List<Map<String, Object>> findObjects();
	int updateObject(Menus entity);
}
