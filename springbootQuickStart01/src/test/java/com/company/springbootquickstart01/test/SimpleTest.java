package com.company.springbootquickstart01.test;

import com.company.springbootquickstart01.codes.common.util.ConstantsUtil;
import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.entity.User;
import com.company.springbootquickstart01.codes.vo.UserVo;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleTest {

    private static int value = 20;

    @Test
    public void test() {
        User entity = ServiceUtil.createEntity(new User(), 111l);
        System.out.println(1);
    }

    @Test
    public void test1() {
        String name = ConstantsUtil.UUID_COOKIE_NAME;
        String value = ConstantsUtil.sex.FEMAN.getValue();
        System.out.println(1);
    }

    @Test
    public void test2() {
        a(1);
    }

    private void a(int i){
        System.out.println(i);
        i = i + 1;
        System.out.println(i);
        System.out.println(i);
    }
}
