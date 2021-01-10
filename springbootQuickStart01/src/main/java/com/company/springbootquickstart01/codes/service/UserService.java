package com.company.springbootquickstart01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.dao.UserDao;
import com.company.springbootquickstart01.entity.UserDo;
import com.company.springbootquickstart01.param.UpdateUserParam;

public interface UserService extends IService<UserDo> {
    UserDo findUserById(Long id);

    void updateUser(UpdateUserParam param);
}
