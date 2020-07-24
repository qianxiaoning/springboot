package com.qxn.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qxn.util.CookieUtil;
import com.qxn.util.IPUtil;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.qxn.dubbo.service.DubboUserService;
import com.qxn.pojo.User;
//import com.qxn.util.CookieUtil;
//import com.qxn.util.IPUtil;
//import com.qxn.util.ObjectMapperUtil;
//import com.qxn.util.ThreadLocalUtil;
import com.qxn.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	@Reference(check = false)//不需要校验
	private DubboUserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	private static final String TICKET = "C2CSYSTEM_TICKET";
	private static final String USERNAME = "C2CSYSTEM_USERNAME";
//	//注册http://www.c2c-system.com/user/register.html
//	//登录http://www.c2c-system.com/user/login.html

	@PostMapping("/doRegister")
	public SysResult saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return SysResult.success("注册成功");
	}
	/**
	 * ticketCookie.setMaxAge(>0);存活的生命周期
	 * setMaxAge(0);立即删除
	 * setMaxAge(-1);当会话关闭时，删除cookie
	 */
	@PostMapping("/doLogin")
	public SysResult doLogin2(@RequestBody User user,HttpServletResponse response,HttpServletRequest request) {
		//1.获取ip信息
		String ip = IPUtil.getIpAddr(request);
		//2.完成用户信息的校验
		String ticket = userService.doLogin2(user,ip);
		if(StringUtils.isEmpty(ticket)) {
			//返回不正确
			return SysResult.fail("用户名或密码错误");
		}
		//3.将ticket,username写入cookie
		CookieUtil.addCookie(request, response, TICKET, ticket,
				7*24*3600, "c2c-system.com");
		CookieUtil.addCookie(request, response, USERNAME,
				user.getUsername(),7*24*3600, "c2c-system.com");
		return SysResult.success();
	}
	public SysResult doLogin1(@RequestBody User user,HttpServletResponse response,HttpServletRequest request) {
		String ticket = userService.doLogin1(user);
		if(StringUtils.isEmpty(ticket)) {
			//返回不正确
			return SysResult.fail("用户名或密码错误");
		}
		//将ticket保存到客户端的cookie中
		Cookie ticketCookie = new Cookie(TICKET, ticket);
		//7天有效
		ticketCookie.setMaxAge(7*24*3600);
		//cookie的权限设定（路径）根目录下
		ticketCookie.setPath("/");
		//cookie实现共享，不写www
		ticketCookie.setDomain("c2c-system.com");
		response.addCookie(ticketCookie);
		return SysResult.success();
	}
//
////	http://www.c2c-system.com/user/logout
//	/**
//	 *前提：获取cookies
//	 *1.获取C2CSYSTEM_TICKET的Cookie的值ticket
//	 *2.删除cookie 名称为C2CSYSTEM_TICKET
//	 *3.删除redis 根据ticket删除redis
//	 */
	@GetMapping("/logout")
	public SysResult logout2(HttpServletRequest request,HttpServletResponse response) {
		String username = CookieUtil.getCookieValue(request, USERNAME);

		if(!StringUtils.isEmpty(username)) {
			jedisCluster.del(username);
			CookieUtil.addCookie(request, response, TICKET, "",
					0, "c2c-system.com");
			CookieUtil.addCookie(request, response, USERNAME, "",
					0, "c2c-system.com");
		}
		return SysResult.success("登出完成");
	}
	public SysResult logout1(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String ticket = null;
		if (cookies.length>0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(TICKET)) {
					ticket = cookie.getValue();
					break;
				}
			}
		}
		if(!StringUtils.isEmpty(ticket)) {
			jedisCluster.del(ticket);
			Cookie cookie = new Cookie(TICKET, "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setDomain("c2c-system.com");
			response.addCookie(cookie);
		}
		return SysResult.success("登出完成");
	}
}
