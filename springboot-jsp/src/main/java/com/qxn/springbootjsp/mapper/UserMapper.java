package com.qxn.springbootjsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxn.springbootjsp.pojo.User;

//@Mapper交给spring管理
public interface UserMapper extends BaseMapper<User> {
}
