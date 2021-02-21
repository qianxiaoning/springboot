package com.company.springbootquickstart01.codes.common.listener.customListener;

import com.company.springbootquickstart01.codes.entity.UserDo;
import org.springframework.context.ApplicationEvent;

//自定义事件
public class AEvent extends ApplicationEvent {
    private UserDo userDo;

    public AEvent(Object source) {
        super(source);
    }

    public AEvent(Object source, UserDo userDo) {
        super(source);
        this.userDo = userDo;
    }

    public UserDo getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDo userDo) {
        this.userDo = userDo;
    }
}
