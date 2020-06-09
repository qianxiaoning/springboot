package com.qxn.springbootjsp.service;

import com.qxn.springbootjsp.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findUsers();
}
