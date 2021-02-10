package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.springbootquickstart01.codes.common.globalException.ServiceException;
import com.company.springbootquickstart01.codes.common.util.CookieUtil;
import com.company.springbootquickstart01.codes.common.util.IPUtil;
import com.company.springbootquickstart01.codes.common.util.JasypUtil;
import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.dao.UserDao;
import com.company.springbootquickstart01.codes.entity.UserDo;
import com.company.springbootquickstart01.libs.redis.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class NormalWayServiceImpl extends ServiceImpl<UserDao, UserDo> implements NormalWayService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public LoginVo login(NormalWayParam param, HttpServletResponse response, HttpServletRequest request) {
        //查询账户是否存在
        QueryWrapper<UserDo> qw = new QueryWrapper<>();
        qw.eq("account",param.getAccount());
        UserDo userDo = userDao.selectOne(qw);
        if(userDo == null){
            throw new ServiceException("账号不存在");
        }
        //密码比较
        String password = JasypUtil.decryptWithSHA512(userDo.getPassword());
        if(password.equals(param.getPassword())){
            //成功登录
            LoginVo loginVo = new LoginVo();
            BeanUtils.copyProperties(userDo, loginVo);
            //uuid和用户ip生成token，将token与用户挂钩存入redis，uuid存入cookie
            String uuid = "uuid" + UUID.randomUUID().toString();
            String ip = "ip"+IPUtil.getIpAddr(request).replace(":","：");
            String token = uuid + ip;
            redisUtil.set("UserToken:"+token,loginVo,7*24*3600);
            CookieUtil.addCookie(request, response, "user_uuid", uuid,
                    7*24*3600, "localhost");//c2c-system.com
            return loginVo;
        }else{
            throw new ServiceException("账号或密码不正确");
        }
    }

    @Override
    public void register(NormalWayParam param) {
        //查询账号是否已存在
        QueryWrapper<UserDo> qw = new QueryWrapper<>();
        qw.eq("account",param.getAccount());
        UserDo userDo = userDao.selectOne(qw);
        if(userDo != null){
            throw new ServiceException("账号已存在");
        }
        //创建账户
        UserDo userDo1 = new UserDo();
        //密码加密
        param.setPassword(JasypUtil.encryptWithSHA512(param.getPassword()));
        BeanUtils.copyProperties(param, userDo1);
        //调用mybatisplus的雪花算法
        Long id = IdWorker.getId(userDo1);
        userDo1.setId(id);
        UserDo entity = ServiceUtil.createEntity(userDo1, id);
        userDao.insert(entity);
    }
}
