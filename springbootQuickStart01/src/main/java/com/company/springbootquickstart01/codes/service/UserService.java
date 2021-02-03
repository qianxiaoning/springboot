package com.company.springbootquickstart01.codes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.codes.entity.UserDo;
import com.company.springbootquickstart01.codes.param.UpdateUserParam;

public interface UserService extends IService<UserDo> {
    UserDo findUserById(Long id);

    void updateUser(UpdateUserParam param);
}
