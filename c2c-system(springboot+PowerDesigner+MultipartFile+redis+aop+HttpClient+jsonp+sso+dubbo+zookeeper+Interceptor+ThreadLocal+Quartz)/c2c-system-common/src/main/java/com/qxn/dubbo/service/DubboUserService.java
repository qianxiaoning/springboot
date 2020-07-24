package com.qxn.dubbo.service;

import com.qxn.pojo.User;

public interface DubboUserService {

    void saveUser(User user);

    String doLogin1(User user);

    String doLogin2(User user,String ip);

}
