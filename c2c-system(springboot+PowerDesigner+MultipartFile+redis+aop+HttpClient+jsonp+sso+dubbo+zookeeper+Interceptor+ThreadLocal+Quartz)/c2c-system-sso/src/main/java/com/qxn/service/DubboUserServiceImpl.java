package com.qxn.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.util.ObjectUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qxn.dubbo.service.DubboUserService;
//编辑接口的实现类
import com.qxn.mapper.UserMapper;
import com.qxn.pojo.User;
import com.qxn.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;
@Service
public class DubboUserServiceImpl implements DubboUserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster; 

	@Override
	public void saveUser(User user) {
		//加盐值
//		String soltString = user.getPassword()+"cn.tedu";
		//防止email为空报错，用电话号码代替
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass)
			.setEmail(user.getPhone())
			.setCreated(new Date())
			.setUpdated(user.getCreated());
		userMapper.insert(user);
	}
	/**
	 * 1.校验用户是否正确
	 * 2.需要将用户信息写入redis中
	 */
	@Override
	public String doLogin2(User user,String ip) {
		User userDB = findUserByUP(user);
		if (userDB==null) {
			//说明 用户名和密码不正确
			return null;
		}
		//表示用户信息正确，保存ticket/ip/userJson
		String uuid = UUID.randomUUID().toString();
		String ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());
		userDB.setPassword("脱敏");
		String userJSON = ObjectMapperUtil.toJSON(userDB);
		Map<String,String> hash = new HashMap<String,String>();
		hash.put("C2CSYSTEM_TICKET", ticket);
		hash.put("C2CSYSTEM_USERJSON", userJSON);
		hash.put("C2CSYSTEM_IP", ip);
		//jedisCluster.hmset批量插入值hash，
		//Username:{C2CSYSTEM_TICKET:ticket,"USERJSON":userJson,"IP":用户ip}
		String result = jedisCluster.hmset(user.getUsername(), hash);
		if (!result.equalsIgnoreCase("OK")) {
			return null;
		}
		//超时时间7天
		jedisCluster.expire(user.getUsername(), 7*24*3600);
		return ticket;
	}
	/**
	 * 1.根据用户名、密码查询数据库
	 * 结果：没有记录 说明用户名密码不正确return null
	 * 2.生成ticket(加密后的秘钥)，userJSON串，将数据保存到redis中
	 * 3.返回ticket
	 */
	@Override
	public String doLogin1(User user) {
		User userDB = findUserByUP(user);
		if(userDB != null) {
			/**
			 * 1.生成秘钥
			 * 2.将敏感数据脱敏，将user对象转化为json串
			 * 3.
			 */
			String uuid = UUID.randomUUID().toString();
			String ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());
			userDB.setPassword("***");
			String userJSON = ObjectMapperUtil.toJSON(userDB);
			jedisCluster.setex(ticket, 7*24*3600, userJSON);
			return ticket;
		}
		return null;
	}
	public User findUserByUP(User user) {
		//md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		//set到user中
		user.setPassword(md5Pass);
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		User userDB = userMapper.selectOne(queryWrapper);
		return userDB;
	}
}
