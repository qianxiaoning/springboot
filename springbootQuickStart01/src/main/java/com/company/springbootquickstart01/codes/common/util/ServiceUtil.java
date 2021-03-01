package com.company.springbootquickstart01.codes.common.util;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;

public class ServiceUtil {
    //创建对象时，设置属性值
    public static <T> T createEntity(T entity,Long userId) {
        try {
            //反射起点，字节码对象
            Class<?> aClass = entity.getClass();
            //获取要修改的4个属性
            Field createTime = aClass.getDeclaredField("createTime");
            Field createBy = aClass.getDeclaredField("createBy");
            Field updateTime = aClass.getDeclaredField("updateTime");
            Field updateBy = aClass.getDeclaredField("updateBy");
            //获取私有属性访问权限
            createTime.setAccessible(true);
            createBy.setAccessible(true);
            updateTime.setAccessible(true);
            updateBy.setAccessible(true);
            //给指定属性赋值
            LocalDateTime date = LocalDateTime.now();
            createTime.set(entity,date);
            createBy.set(entity,userId);
            updateTime.set(entity,date);
            updateBy.set(entity,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
    //修改对象时，设置属性值
    public static <T> T updateEntity(T entity,Long userId) {
        try {
            //反射起点，字节码对象
            Class<?> aClass = entity.getClass();
            //获取要修改的4个属性
            Field updateTime = aClass.getDeclaredField("updateTime");
            Field updateBy = aClass.getDeclaredField("updateBy");
            //获取私有属性访问权限
            updateTime.setAccessible(true);
            updateBy.setAccessible(true);
            //给指定属性赋值
            LocalDateTime date = LocalDateTime.now();
            updateTime.set(entity,date);
            updateBy.set(entity,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
    //删除对象时，设置属性值
    public static <T> T deleteEntity(T entity,Long userId) {
        try {
            //反射起点，字节码对象
            Class<?> aClass = entity.getClass();
            //获取要修改的4个属性
            Field delTime = aClass.getDeclaredField("delTime");
            Field delBy = aClass.getDeclaredField("delBy");
            //获取私有属性访问权限
            delTime.setAccessible(true);
            delBy.setAccessible(true);
            //给指定属性赋值
            LocalDateTime date = LocalDateTime.now();
            delTime.set(entity,date);
            delBy.set(entity,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}
