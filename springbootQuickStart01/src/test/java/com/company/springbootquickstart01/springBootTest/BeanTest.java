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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {
    @Autowired
    private ATrigger aTrigger;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisOperate redisOperate;
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
    @Test
    public void test3() {
//        System.out.println(redisOperate.llen("b"));
        System.out.println(redisOperate.rpush("userLoginErrorList:" +"a",1));
        redisOperate.del("a");
        Date date = new Date();

        long l = 1636437413816l - 1636437413816l;
        long aa = 500000l;
        System.out.println(l/600000);


        redisOperate.rpush("a",new Date());
        redisOperate.rpush("a",new Date());
        redisOperate.rpush("a",new Date());
//        redisOperate.set("a",2);
//        redisOperate.set("a",3);
//        redisOperate.get("a");
        List<Date> a = redisOperate.lrange("a", 0, 0, Date.class);
//        System.out.println(redisOperate.lrange("a",0,0,Date.class));
//        redisOperate.get(Arrays.asList(redisOperate.get("a")));
//        redisOperate.lrem("a",1, redisOperate.lrange("a",0,0,Date.class).get(0).getTime());
//        System.out.println(1);
    }
}
