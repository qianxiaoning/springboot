package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.codes.entity.UserDo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NormalWayService extends IService<UserDo> {

    void register(NormalWayParam param);

    LoginVo login(NormalWayParam param, HttpServletResponse response, HttpServletRequest request);
}
