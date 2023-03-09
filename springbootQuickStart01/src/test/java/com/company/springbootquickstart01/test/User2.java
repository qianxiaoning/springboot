package com.company.springbootquickstart01.test;

import java.io.Serializable;
import java.util.List;

public class User2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nickName;

    private String users;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
