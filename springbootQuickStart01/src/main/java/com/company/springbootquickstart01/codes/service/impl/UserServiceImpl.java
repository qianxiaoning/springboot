package com.company.springbootquickstart01.codes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.springbootquickstart01.codes.common.globalException.ServiceException;
import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.common.util.ThreadLocalUtil;
import com.company.springbootquickstart01.codes.mapper.UserMapper;
import com.company.springbootquickstart01.codes.entity.User1;
import com.company.springbootquickstart01.codes.param.UpdateUserParam;
import com.company.springbootquickstart01.codes.service.UserService;
import com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay.LoginVo;
import com.company.springbootquickstart01.libs.redis.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User1> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User1 findUserById(Long id) {
        if(id==null) {
            throw new IllegalArgumentException("id不能为空");
        }
//        User user = UserMapper.findUserById(id);
        User1 user = userMapper.selectById(id);
        if(user ==null) {
            throw new ServiceException("用户不存在");
        }
        //redis测试
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
//        redisTemplate.opsForValue().set("user",user);
//        User userRedis = (User)redisTemplate.opsForValue().get("user");
//        redisUtil.set("userRedisUtil",user);
//        User userRedisUtil = (User) redisUtil.get("userRedisUtil");
        //
        return user;
    }

    @Override
    public void updateUser(UpdateUserParam param) {
        User1 user = baseMapper.selectById(param.getId());
        if (user == null){
            throw new ServiceException("id不存在");
        }else{
            BeanUtils.copyProperties(param, user);
            LoginVo userInfo = ThreadLocalUtil.get("userInfo");
            User1 entity = ServiceUtil.updateEntity(user, userInfo.getId());
            userMapper.updateById(entity);
        }
    }
}
