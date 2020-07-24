package com.qxn.service;

import org.apache.ibatis.annotations.Select;

public interface UserService {
    boolean checkUser(String param, Integer type);
}
