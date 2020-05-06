package com.qxn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.pj.companys.dao.CompanysDao;
//指定junit框架的启动运行类
@RunWith(SpringRunner.class)
//此测试类交给spring管理
@SpringBootTest
public class CompanysDaoTests {
	@Autowired
	private CompanysDao companysDao;
	@Test
	public void testDeleteObject() {
		int rows = companysDao.deleteObject(5);
		System.out.println("rows="+rows);
	}
	@Test
	public void testDeleteObjects() {
		int rows = companysDao.deleteObjects(5,6);
		System.out.println("rows="+rows);
	}
}
