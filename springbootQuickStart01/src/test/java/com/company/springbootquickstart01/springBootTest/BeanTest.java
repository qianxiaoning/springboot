package com.company.springbootquickstart01.springBootTest;

import com.company.springbootquickstart01.codes.common.util.JasypUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {
//    @Autowired
//    private LogsDao logsDao;
//    @Test
//    public void testGetRowCount() {
//        int rowCount = logsDao.getRowCount("sasa");
//        System.out.println(rowCount);
//    }
    @Test
    public void test1() {
        JasypUtil.main();
    }
}
