package com.qxn.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.qxn.pj.sys.entity.Depts;

public interface DeptsService {

	List<Map<String, Object>> findObjects();

	int insertObject(Depts depts);

	int updateObject(Depts depts);

	int deleteObject(Integer id);
}
