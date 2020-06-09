package com.qxn.springbootjsp.serviceImpl;

import com.qxn.springbootjsp.mapper.UserMapper;
import com.qxn.springbootjsp.pojo.User;
import com.qxn.springbootjsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUsers() {
        List<User> userList = userMapper.selectList(null);
        return userList;
    }
}
