package com.company.springbootquickstart01.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.springbootquickstart01.entity.UserDo;

public interface UserDao extends BaseMapper<UserDo> {
    UserDo findUserById(Long id);
}
