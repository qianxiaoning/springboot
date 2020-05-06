package com.qxn.pj.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qxn.pj.common.annotation.RequiredLog;
import com.qxn.pj.common.exception.ServiceException;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.dao.UsersDao;
import com.qxn.pj.sys.dao.UsersRolesDao;
import com.qxn.pj.sys.entity.Users;
import com.qxn.pj.sys.service.UsersService;
import com.qxn.pj.sys.vo.UserDeptRoleIds;
import com.qxn.pj.sys.vo.UserDeptRolesPra;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao usersDao;	
	@Autowired
	private UsersRolesDao usersRolesDao;	
	//mybatisAssociationPra练习
	@Override
	public UserDeptRolesPra findUserDeptRolesById(Integer userId) {
		if(userId==null||userId<1) {
			throw new IllegalArgumentException(
					"id无效");
		}
		UserDeptRolesPra userDeptRolesPra = usersDao.findUserDeptRolesById(userId);
		return userDeptRolesPra;
	}
	@Override
	public UserDeptRoleIds findUserDeptRoleIdsById(Integer userId) {
		if(userId==null||userId<1) {
			throw new IllegalArgumentException(
					"id无效");
		}
		UserDeptRoleIds userDeptRoleIds = usersDao.findUserDeptRoleIdsById(userId);
		return userDeptRoleIds;
	}
	@Override
	public int insertUser(UserDeptRoleIds userDeptRoleIds) {
		System.out.println(userDeptRoleIds.getUsername());
		if(StringUtils.isEmpty(userDeptRoleIds.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		String sourcePassword = userDeptRoleIds.getPassword();
		if(StringUtils.isEmpty(sourcePassword)) {
			throw new IllegalArgumentException("密码不能为空");
		}
		//uuid作为盐值
		String salt = UUID.randomUUID().toString();
		/*md5加密，只能加密不能解密，相同内容加密结果是一样的，
		 * 所以得把salt保存，每次把密码和盐值加密和数据库中密码比较，
		 * 数据库中密码是原密码经过salt加密后的密码
		 * */
		//shiro框架给密码加密
		SimpleHash sh = new SimpleHash(
				"md5", //algorithmName算法名称
				sourcePassword, //source原密码
				salt, //salt盐值
				1//iterations加密此数，可以对加密结果进行再加密
				);
		//把加密过的密码和盐值存入数据库
		userDeptRoleIds.setSalt(salt);
		userDeptRoleIds.setPassword(sh.toHex());
		int rows = usersDao.insertUser(userDeptRoleIds);
		System.out.println(rows);
		int rolesRows = usersRolesDao.insertRoles(userDeptRoleIds);
		return rows;
	}
	@Cacheable(value = "usersCache")
	@Override
	public PageObject<UserDeptRoleIds> findPageObject(String username, Integer pageCurrent) {
		//测试业务层缓存生效没
		System.out.println("findPageObject");
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1) {
			//运行时异常
			throw new IllegalArgumentException("当前页码值无效");
		}
		//2.查询记录总数，校验
		int rowCount = usersDao.getRowCount(username);
		if(rowCount==0) {
			//业务层异常
			throw new ServiceException("没有找到对应记录");
		}
		//3.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;		
		List<UserDeptRoleIds> records = usersDao.findPageObjects(username, startIndex, pageSize);
		//4.查询结果封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@RequiredLog("禁用启用")
	@Override
	public int updateValidByUserId(Users users) {
		int usersId = users.getId();
		if(usersId<1) {
			throw new IllegalArgumentException("id值无效");
		}
		int rows = usersDao.updateValidByUserId(users);		
		return rows;
	}
	//spEL表达式 #userDeptRoleIds.id
	//更新缓存
//	@CachePut(value = "usersCache",key = "#userDeptRoleIds.id")
	//删除缓存，beforeInvocation = false默认更新成功后再清缓存
	@CacheEvict(value = "usersCache",
			key = "#userDeptRoleIds.id")
	@RequiredLog("修改用户")
	@Override
	public int updateUserDeptRoleIdsById(UserDeptRoleIds userDeptRoleIds) {
		int usersId = userDeptRoleIds.getId();
		if(usersId<1){
			throw new IllegalArgumentException("id值无效");
		}
		int rows = usersDao.updateInfo(userDeptRoleIds);
		usersRolesDao.deleteObjectsByUserId(usersId);
		usersRolesDao.insertRoles(userDeptRoleIds);		
		return rows;
	}
}
