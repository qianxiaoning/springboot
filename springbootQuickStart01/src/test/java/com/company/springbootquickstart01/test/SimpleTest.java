package com.company.springbootquickstart01.test;

import com.company.springbootquickstart01.codes.common.util.ConstantsUtil;
import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.entity.UserDo;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void test(){
        UserDo entity = ServiceUtil.createEntity(new UserDo(), 111l);
        System.out.println(1);
    }
    @Test
    public void test1(){
        String name = ConstantsUtil.UUID_COOKIE_NAME;
        String value = ConstantsUtil.sex.FEMAN.getValue();
        System.out.println(1);
    }
}
