package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.codes.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NormalWayService extends IService<User> {

    void register(NormalWayParam param);

    LoginVo login(NormalWayParam param, HttpServletRequest request,HttpServletResponse response);

    void logout(HttpServletRequest request,HttpServletResponse response);
}
