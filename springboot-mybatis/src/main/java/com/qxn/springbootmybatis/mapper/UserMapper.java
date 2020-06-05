package com.qxn.springbootmybatis.mapper;

import com.qxn.springbootmybatis.pojo.User;

import java.util.List;

//@Mapper//@Mapper交给spring管理
public interface UserMapper {
    List<User> findAllUser();
}
