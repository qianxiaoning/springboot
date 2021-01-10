package com.company.springbootquickstart01.common.util;

import com.company.springbootquickstart01.entity.UserDo;

public class ThreadLocalUtil {
private static ThreadLocal<UserDo> thread = new ThreadLocal<UserDo>();
public static void set(UserDo user) {
    thread.set(user);
}
public static UserDo get() {
    return thread.get();
}
//防止内存泄漏
public static void remove() {
    thread.remove();
}
}
