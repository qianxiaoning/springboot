package com.qxn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
/*
 * @RunWith指定由谁调用测试类对象的方法进行单元测试
 * @SpringBootTest表示描述的对象交给spring管理
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot01ApplicationTests {
	@Autowired
	private ApplicationContext ctx;
	@Test
	public void testCtx() {
		//AnnotationConfigApplicationContext
		System.out.println(ctx);
	}
}

