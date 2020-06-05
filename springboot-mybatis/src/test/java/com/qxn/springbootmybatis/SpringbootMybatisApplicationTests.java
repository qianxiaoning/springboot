package com.qxn.springbootmybatis;

import com.qxn.springbootmybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testFindUser() {
        System.out.println(userMapper.getClass());
        System.out.println(userMapper.findAllUser());
    }
}
