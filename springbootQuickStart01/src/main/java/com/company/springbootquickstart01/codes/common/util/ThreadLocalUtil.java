package com.company.springbootquickstart01.codes.common.util;

import java.util.HashMap;
import java.util.Map;

//ThreadLocal，多线程有着自己的变量副本，保证多线程共享变量安全
public class ThreadLocalUtil {

    private static final ThreadLocal<Map<String,Object>> threadLocal =
            ThreadLocal.withInitial(() -> new HashMap<>(8));

    private static Map<String,Object> getThreadLocal(){
        return threadLocal.get();
    }

    public static void put(String key,Object object) {
        getThreadLocal().put(key,object);
    }

    public static <T> T get(String key) {
        return (T)getThreadLocal().get(key);
    }

    public static <T> T removeOne(String key) {
        return (T)getThreadLocal().remove(key);
    }

    public static void removeAll() {
        getThreadLocal().clear();
    }

    //清除threadLocal，防止内存泄漏
    public static void clear() {
        threadLocal.remove();
    }
}
