package com.qxn.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qxn.pj.sys.dao.LogsDao;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.service.LogsService;
@Service
@Transactional
public class LogsServiceImpl implements LogsService{
	@Autowired
	private LogsDao logsDao;		
	@Override
	public List<Logs> findLogs() {
		List<Logs> list = logsDao.findLogs();
		return list;
	}

}
