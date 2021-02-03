package com.company.springbootquickstart01.test;

import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.entity.UserDo;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void test(){
        UserDo entity = ServiceUtil.createEntity(new UserDo(), 111l);
        System.out.println(1);
    }
}
