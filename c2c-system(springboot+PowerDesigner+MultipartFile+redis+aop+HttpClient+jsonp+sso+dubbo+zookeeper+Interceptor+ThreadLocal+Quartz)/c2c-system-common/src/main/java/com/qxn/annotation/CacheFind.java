package com.qxn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//作用范围
@Target(ElementType.METHOD)//注解修饰范围
public @interface CacheFind {
    String key() default "";//key默认值
    int seconds() default 0;//默认无过期时间
}
