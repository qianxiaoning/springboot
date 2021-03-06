package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.springbootquickstart01.codes.common.globalException.ServiceException;
import com.company.springbootquickstart01.codes.common.util.*;
import com.company.springbootquickstart01.codes.mapper.UserMapper;
import com.company.springbootquickstart01.codes.entity.User;
import com.company.springbootquickstart01.libs.redis.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class NormalWayServiceImpl extends ServiceImpl<UserMapper, User> implements NormalWayService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public LoginVo login(NormalWayParam param, HttpServletRequest request, HttpServletResponse response) {
        //查询账户是否存在
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account",param.getAccount());
        User user = userMapper.selectOne(qw);
        if(user == null){
            throw new ServiceException("账号不存在");
        }
        //密码比较
        String password = JasypUtil.decryptWithSHA512(user.getPassword());
        if(password.equals(param.getPassword())){
            //成功登录
            LoginVo loginVo = new LoginVo();
            BeanUtils.copyProperties(user, loginVo);
            //uuid和用户ip生成token，将token与用户挂钩存入redis，uuid存入cookie
            String uuid = "uuid" + UUID.randomUUID().toString();
            String ip = "ip"+IPUtil.getIpAddr(request).replace(":","：");
            String token = uuid + ip;
            redisUtil.set("UserToken:"+token,loginVo,7*24*3600);
            CookieUtil.addCookie(request, response, ConstantsUtil.UUID_COOKIE_NAME, uuid,
                    7*24*3600, ConstantsUtil.COOKIE_DOMAIN);//c2c-system.com
            return loginVo;
        }else{
            throw new ServiceException("账号或密码不正确");
        }
    }

    @Override
    public void logout(HttpServletRequest request,HttpServletResponse response) {
        //清除redis
        String uuid = CookieUtil.getCookieValue(request, ConstantsUtil.UUID_COOKIE_NAME);
        String ip = "ip"+ IPUtil.getIpAddr(request).replace(":","：");
        String token = uuid + ip;
        redisUtil.del("UserToken:" + token);
        //清除cookie，cookie过期时间设为0覆盖
        CookieUtil.addCookie(request, response, ConstantsUtil.UUID_COOKIE_NAME, null,
                0, ConstantsUtil.COOKIE_DOMAIN);
    }

    @Override
    public void register(NormalWayParam param) {
        //查询账号是否已存在
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account",param.getAccount());
        //查询账户
        User user = userMapper.selectOne(qw);
        if(user != null){
            throw new ServiceException("账号已存在");
        }
        //密码加密
        param.setPassword(JasypUtil.encryptWithSHA512(param.getPassword()));
        BeanUtils.copyProperties(param, user);
        //调用mybatisplus的雪花算法
        Long id = IdWorker.getId(user);
        user.setId(id);
        User entity = ServiceUtil.createEntity(user, id);
        userMapper.insert(entity);
    }
}
