package com.company.springbootquickstart01.codes.common.listener.customListener;

import com.company.springbootquickstart01.codes.entity.User1;
import org.springframework.context.ApplicationEvent;

//自定义事件
public class AEvent extends ApplicationEvent {
    private User1 user;

    public AEvent(Object source) {
        super(source);
    }

    public AEvent(Object source, User1 user) {
        super(source);
        this.user = user;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }
}
