package com.company.springbootquickstart01.codes.common.listener.customListener;

import com.company.springbootquickstart01.codes.entity.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

//触发器
@Service
public class ATrigger {
    @Autowired
    private ApplicationContext applicationContext;
    //发布事件
    public void queryUser(){
        User1 user = new User1();
        user.setAccount("aaa");
        AEvent aEvent = new AEvent(this, user);
        applicationContext.publishEvent(aEvent);
    }
}
