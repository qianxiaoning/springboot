package com.company.springbootquickstart01.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.springbootquickstart01.codes.common.util.ConstantsUtil;
import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.entity.User;
import com.company.springbootquickstart01.codes.vo.UserVo;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
//        String name = ConstantsUtil.UUID_COOKIE_NAME;
//        String value = ConstantsUtil.sex.FEMAN.getValue();
//        System.out.println(1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String format = sdf.format(new Date());
        String a = "    s  q  ";
        String a1 = "        S       ";
        System.out.println(a.equalsIgnoreCase(a1));
    }

    @Test
    public void test2() {
        com.company.springbootquickstart01.test.User user = new com.company.springbootquickstart01.test.User();
        user.setId(1l);
        user.setNickName("张");
        List<com.company.springbootquickstart01.test.User> users = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            com.company.springbootquickstart01.test.User a = new com.company.springbootquickstart01.test.User();
            a.setId(Long.valueOf(i));
            a.setNickName("张" + i);
            users.add(a);
        }
        user.setUsers(users);
        String jsonString = JSON.toJSONString(user);
        User2 user2 = JSONObject.parseObject(jsonString, User2.class);
        System.out.println(1);
    }


    private void a(int i){
//        Long a = null;
//        String s = String.valueOf(a)
//        System.out.println(1);
    }

    @Test
    public void test3() {
        User user = new User();
        user.setAccount("ss");
        Map a = (Map) user;
        a.get("ss");
        System.out.println(1);
    }
}
