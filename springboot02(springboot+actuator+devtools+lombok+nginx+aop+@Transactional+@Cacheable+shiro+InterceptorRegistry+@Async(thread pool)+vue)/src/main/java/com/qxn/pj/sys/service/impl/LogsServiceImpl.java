package com.qxn.pj.sys.service.impl;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qxn.pj.common.annotation.RequiredTime;
import com.qxn.pj.common.exception.ServiceException;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.dao.LogsDao;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.service.LogsService;
@Service
@Transactional
public class LogsServiceImpl implements LogsService{
	@Autowired
	private LogsDao logsDao;
	@RequiredTime
	@Override
	public PageObject<Logs> findPageObject(String username, Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1) {
			//运行时异常
			throw new IllegalArgumentException("当前页码值无效");
		}
		//2.查询记录总数，校验
		int rowCount = logsDao.getRowCount(username);
		if(rowCount==0) {
			//业务层异常
			throw new ServiceException("没有找到对应记录");
		}
		//3.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;		
		List<Logs> records = logsDao.findPageObjects(username, startIndex, pageSize);
		//4.查询结果封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}	
	@RequiresPermissions("sys:log:delete")
	@Override
	public int deleteObjects(Integer ...ids) {
		//校验，删除，返回
		System.out.println(ids);
		if(ids==null||ids.length==0) {
			throw new IllegalArgumentException("参数值无效");
		}
		int counts = logsDao.deleteObjects(ids);
		if(counts==0) {
			throw new ServiceException("记录不存在");
		}
		return counts;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	@Async//加注解，用异步方式写日志，默认池对象是ThreadPoolTaskExecutor
//	@Async//使用默认getAsyncExecutor方法
	@Async("asyncExecutor")//自定义方法asyncExecutor
	@Override
//	public void insertLog(Logs entity) {
//		//看当前线程名称
//		String tName = Thread.currentThread().getName();
//		System.out.println("insertLog.thread.name = " + tName);
//		try {
//			Thread.sleep(10000);
//		} catch (Exception e) {
//		}
//		int rows = logsDao.insertLog(entity);
//	}
	public Future<Integer> insertLog(Logs entity) {
		//看当前线程名称
		String tName = Thread.currentThread().getName();
		System.out.println("insertLog.thread.name = " + tName);
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
		}
		int rows = logsDao.insertLog(entity);
		return new AsyncResult<Integer>(rows);
	}
}
