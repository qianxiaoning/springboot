package com.company.springbootquickstart01.codes.common.listener.customListener;

import com.company.springbootquickstart01.codes.entity.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//自定义监听器
@Component
public class AEventListener implements ApplicationListener<AEvent> {
    @Override
    public void onApplicationEvent(AEvent aEvent) {
        //获取事件中的信息
        User user = aEvent.getUser();
        //处理事件，通知别的微服务或者逻辑处理
        //...
        System.out.println(user);
    }
}
