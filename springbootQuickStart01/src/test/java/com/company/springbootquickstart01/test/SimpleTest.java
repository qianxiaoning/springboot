package com.company.springbootquickstart01.test;

import com.company.springbootquickstart01.codes.common.util.ConstantsUtil;
import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.entity.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;

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
        int[] x = {1,2};
        for (int i = 0; i < x.length; i++) {
            System.out.println(i);
        }

        ArrayList<Map> aa = new ArrayList<>();
        Map<String, Object> a = new HashMap<>();
        a.put("id", 1);
        a.put("a", 1);
        aa.add(a);
        Map<String, Object> b = new HashMap<>();
        b.put("id", 1);
        b.put("b", 2);
        aa.add(b);
        Map<String, Object> c = new HashMap<>();
        c.put("id", 1);
        c.put("c", 3);
        aa.add(c);
        a.putAll(b);
        a.putAll(c);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        int i = 0;
        for (Map o : aa) {
            o.put("idd", a.remove("id"));
            System.out.println(o.get("idd"));
            i++;
        }


//        a.put("idd", a.remove("id"));
        System.out.println(a);
    }
}
