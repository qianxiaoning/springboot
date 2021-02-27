package com.company.springbootquickstart01.codes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.codes.entity.User1;
import com.company.springbootquickstart01.codes.param.UpdateUserParam;

public interface UserService extends IService<User1> {
    User1 findUserById(Long id);

    void updateUser(UpdateUserParam param);
}
