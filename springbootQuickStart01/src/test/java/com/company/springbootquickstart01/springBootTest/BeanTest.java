package com.company.springbootquickstart01.springBootTest;

import com.company.springbootquickstart01.codes.common.entity.BasePojo;
import com.company.springbootquickstart01.codes.common.listener.customListener.ATrigger;
import com.company.springbootquickstart01.codes.common.util.JasypUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {
    @Autowired
    private ATrigger aTrigger;
    @Autowired
    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    private LogMapper logMapper;
//    @Test
//    public void testGetRowCount() {
//        int rowCount = logMapper.getRowCount("sasa");
//        System.out.println(rowCount);
//    }
    @Test
    public void test1() {
        JasypUtil.main();
    }
    @Test
    public void test2() {
        List<BasePojo> query = jdbcTemplate.query("select 1 where 1=2", new BeanPropertyRowMapper<>(BasePojo.class));
        System.out.println(query);
    }
}
