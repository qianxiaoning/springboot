package com.qxn.pj.sys.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.service.LogsService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogsServiceTests {
	@Autowired
	private LogsService logsService;
	@Test
	public void testFindPageObjects() {
		PageObject<Logs> po = logsService.findPageObject("sasa", 1);
		System.out.println(po);
	}
}
