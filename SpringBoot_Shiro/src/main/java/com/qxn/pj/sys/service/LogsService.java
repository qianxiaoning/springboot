package com.qxn.pj.sys.service;

import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Logs;

public interface LogsService {
	//分页查询
	PageObject<Logs> findPageObject(
			String username,
			Integer pageCurrent);
	int deleteObjects(Integer ...ids);
	int insertLog(Logs entity);
}
