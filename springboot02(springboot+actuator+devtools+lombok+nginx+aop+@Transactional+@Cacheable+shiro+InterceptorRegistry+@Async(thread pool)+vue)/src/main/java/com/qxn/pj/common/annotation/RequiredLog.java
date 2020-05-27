package com.qxn.pj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//运行时有效
@Retention(RetentionPolicy.RUNTIME)
//描述方法
@Target(ElementType.METHOD)
public @interface RequiredLog {
	//属性及默认值
	String value() default "operation";
}
