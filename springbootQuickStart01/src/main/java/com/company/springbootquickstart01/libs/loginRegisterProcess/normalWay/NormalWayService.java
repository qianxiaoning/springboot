package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.codes.entity.UserDo;

public interface NormalWayService extends IService<UserDo> {
    void login(NormalWayParam param);

    void register(NormalWayParam param);
}
