package com.qxn.controller;

import javax.servlet.http.HttpServletRequest;

import com.qxn.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qxn.service.UserService;
//import com.qxn.util.IPUtil;
import com.qxn.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 根据用户信息，实现数据的校验
     * 返回值结果：true已经存在，false可以使用该数据
     */
    @GetMapping("/check/{param}/{type}")
    public JSONPObject checkUser(@PathVariable String param,
                                 @PathVariable Integer type,
                                 String callback) {
        boolean data = userService.checkUser(param, type);
        return new JSONPObject(callback,SysResult.success(data));
    }
    //	http://sso.c2c-system.com/user/queryByTicket/c2cfcfaeda6560f8ebbf1ef5876cdf2c?callback=
    //根据ticket信息，查询用户信息后将数据传给客户机
    @GetMapping("/queryByCookie/{ticket}")
    public JSONPObject queryByCookie1(@PathVariable String ticket,String callback) {
        String userJSON = jedisCluster.get(ticket);
        //ticket过期了，或在redis信息内存淘汰了
        if(StringUtils.isEmpty(userJSON)) {
            return new JSONPObject(callback,SysResult.fail());
        }
        return new JSONPObject(callback,SysResult.success(userJSON));
    }
    @GetMapping("/queryByCookie/{ticket}/{username}")
    public JSONPObject queryByCookie2(
            @PathVariable String ticket,
            @PathVariable String username,
            String callback,
            HttpServletRequest request) {
        //1.校验用户的IP地址
        String ip = IPUtil.getIpAddr(request);
        String localIP = jedisCluster.hget(username, "C2CSYSTEM_IP");
        if(!ip.equalsIgnoreCase(localIP)) {
            return new JSONPObject(callback, SysResult.fail("和redis中ip不一致，要重新登录"));
        }
        //2.校验ticket信息
        String localTicket = jedisCluster.hget(username, "C2CSYSTEM_TICKET");
        if(!ticket.equalsIgnoreCase(localTicket)) {
            return new JSONPObject(callback, SysResult.fail(
                    "和redis中ticket不一致，被后来登录的人挤掉线了，要重新登录"));
        }
        //2.1.短信验证 200
        //2.2.人脸识别/指纹 移动端
        //3.说明用户信息正确
        String userJSON = jedisCluster.hget(username, "C2CSYSTEM_USERJSON");
        return new JSONPObject(callback,SysResult.success(userJSON));
    }
}
