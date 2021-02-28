package com.company.springbootquickstart01.codes.common.listener.customListener;

import com.company.springbootquickstart01.codes.entity.User;
import org.springframework.context.ApplicationEvent;

//自定义事件
public class AEvent extends ApplicationEvent {
    private User user;

    public AEvent(Object source) {
        super(source);
    }

    public AEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
