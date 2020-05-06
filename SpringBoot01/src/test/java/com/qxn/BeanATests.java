package com.qxn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.pj.common.bean.BeanA;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanATests {
	@Autowired
	private BeanA beanA;
	@Test
	public void testBeanA() {
		System.out.println(beanA);
	}
}
