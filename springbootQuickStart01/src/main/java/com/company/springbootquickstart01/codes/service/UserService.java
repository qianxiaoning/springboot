package com.company.springbootquickstart01.codes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.codes.entity.User;
import com.company.springbootquickstart01.codes.param.UpdateUserParam;

public interface UserService extends IService<User> {
    User findUserById(Long id);

    void updateUser(UpdateUserParam param);
}
