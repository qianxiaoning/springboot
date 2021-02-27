package com.company.springbootquickstart01.codes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.springbootquickstart01.codes.entity.User1;

public interface UserMapper extends BaseMapper<User1> {
    User1 findUserById(Long id);
}
