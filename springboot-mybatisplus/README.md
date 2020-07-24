### springboot-mybatisplus
#### springboot整合mybatisplus
```
概要：
1.mybatisPlus:
单表操作，不需要手写sql
```
---
```
流程：
1.mybatis-plus-boot-starter 导包
2.配置文件 mybatis-plus:
3.pojo中，加上注解
@TableName
@TableId(type = IdType.AUTO)//主键，自增
4.UserMapper继承BaseMapper<T>类，可以直接调用BaseMapper进行crud操作
5.SpringbootMybatisPlusApplicationTests 测试类
```
