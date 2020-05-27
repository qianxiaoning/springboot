package com.qxn.pj.sys.service;

import java.util.concurrent.Future;

import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Logs;

public interface LogsService {
	//分页查询
	PageObject<Logs> findPageObject(
			String username,
			Integer pageCurrent);
	int deleteObjects(Integer ...ids);
//	void insertLog(Logs entity);
	Future<Integer> insertLog(Logs entity);
}
