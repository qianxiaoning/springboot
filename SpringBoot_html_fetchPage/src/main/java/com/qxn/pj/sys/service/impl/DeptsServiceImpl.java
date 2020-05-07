package com.qxn.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qxn.pj.common.exception.ServiceException;
import com.qxn.pj.sys.dao.DeptsDao;
import com.qxn.pj.sys.entity.Depts;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.service.DeptsService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class DeptsServiceImpl implements DeptsService{
	@Autowired
	private DeptsDao deptsDao;	
	@Override
	public List<Depts> findDepts() {
		List<Depts> list = deptsDao.findDepts();
		return list;
	}
}
