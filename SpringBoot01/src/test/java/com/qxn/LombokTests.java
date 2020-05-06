package com.qxn;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.pj.companys.pojo.Companys;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LombokTests {
	@Test
	public void testLombok() {
		Companys c = new Companys();
		c.setId(21);
		c.setName("s");
		c.setRemark("s");
		c.setCreatedTime(new Date());
		System.out.println(c.toString());
		c = new Companys(33, "dd", "dd", new Date());
		System.out.println(c);
		c = new Companys().setId(44)
		.setName("sname").setRemark("s")
		.setCreatedTime(new Date());
		//org.slf4j.Logger
		c.doPrint();
	}
}
