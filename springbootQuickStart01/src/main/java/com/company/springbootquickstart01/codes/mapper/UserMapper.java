package com.company.springbootquickstart01.codes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.springbootquickstart01.codes.entity.User;

public interface UserMapper extends BaseMapper<User> {
    User findUserById(Long id);
}
