package com.qxn.pj.sys.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.pj.sys.dao.LogsDao;
import com.qxn.pj.sys.entity.Logs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogsDaoTests {
	@Autowired
	private LogsDao logsDao;
	@Test
	public void testGetRowCount() {
		int rowCount = logsDao.getRowCount("sasa");
		System.out.println(rowCount);
	}
	@Test
	public void testFindPageObjects() {
		List<Logs> list = logsDao.findPageObjects(null, 0, 1);
		for (Logs logs : list) {
			System.out.println(list.size());
			System.out.println(logs);
		}
	}
}
