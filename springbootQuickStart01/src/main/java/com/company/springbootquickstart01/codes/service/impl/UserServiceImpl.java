package com.company.springbootquickstart01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.springbootquickstart01.common.globalException.ServiceException;
import com.company.springbootquickstart01.common.util.RedisUtil;
import com.company.springbootquickstart01.dao.UserDao;
import com.company.springbootquickstart01.entity.UserDo;
import com.company.springbootquickstart01.param.UpdateUserParam;
import com.company.springbootquickstart01.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,UserDo> implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public UserDo findUserById(Long id) {
        if(id==null) {
            throw new IllegalArgumentException("id不能为空");
        }
//        UserDo userDo = userDao.findUserById(id);
        UserDo userDo = userDao.selectById(id);
//        if(userDo==null) {
//            throw new ServiceException("没有数据");
//        }
//        HashMap<String, Object> hm = new HashMap<>();
//        hm.put("id",1);
//        hm.put("name","qxn");
//        redisTemplate.opsForValue().set("map",hm);
//        Object map = redisTemplate.opsForValue().get("map");
//        System.out.println(map);
//        redisTemplate.opsForValue().set("string","string");
//        stringRedisTemplate.opsForValue().set("stringRedis","stringRedis");
//        String stringRedis = stringRedisTemplate.opsForValue().get("stringRedis");
//        System.out.println(stringRedis);
//        redisTemplate.opsForValue().set("userDo",userDo);
//        UserDo userDoRedis = (UserDo)redisTemplate.opsForValue().get("userDo");
//        redisUtil.set("userDoRedisUtil",userDo);
//        UserDo userDoRedisUtil = (UserDo) redisUtil.get("userDoRedisUtil");
        return userDo;
    }

    @Override
    public void updateUser(UpdateUserParam param) {
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(param, userDo);
        userDao.updateById(userDo);
    }
}
