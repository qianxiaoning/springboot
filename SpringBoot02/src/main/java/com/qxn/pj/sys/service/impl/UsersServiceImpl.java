package com.qxn.pj.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qxn.pj.common.annotation.RequiredLog;
import com.qxn.pj.common.exception.ServiceException;
import com.qxn.pj.common.util.ShiroUtils;
import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.dao.UsersDao;
import com.qxn.pj.sys.dao.UsersRolesDao;
import com.qxn.pj.sys.entity.Users;
import com.qxn.pj.sys.service.UsersService;
import com.qxn.pj.sys.vo.EditPassword;
import com.qxn.pj.sys.vo.LoginVo;
import com.qxn.pj.sys.vo.UserDeptRoleIds;
import com.qxn.pj.sys.vo.UserDeptRolesPra;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private UsersRolesDao usersRolesDao;

	// mybatisAssociationPra练习
	@Override
	public UserDeptRolesPra findUserDeptRolesById(Integer userId) {
		if (userId == null || userId < 1) {
			throw new IllegalArgumentException("id无效");
		}
		UserDeptRolesPra userDeptRolesPra = usersDao.findUserDeptRolesById(userId);
		return userDeptRolesPra;
	}

	@Override
	public UserDeptRoleIds findUserDeptRoleIdsById(Integer userId) {
		if (userId == null || userId < 1) {
			throw new IllegalArgumentException("id无效");
		}
		UserDeptRoleIds userDeptRoleIds = usersDao.findUserDeptRoleIdsById(userId);
		return userDeptRoleIds;
	}

	@Override
	public int insertUser(UserDeptRoleIds userDeptRoleIds) {
//		System.out.println(12);
//		System.out.println(userDeptRoleIds.getUsername());
		if (StringUtils.isEmpty(userDeptRoleIds.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		String sourcePassword = userDeptRoleIds.getPassword();
		if (StringUtils.isEmpty(sourcePassword)) {
			throw new IllegalArgumentException("密码不能为空");
		}
		Integer[] roleIds = userDeptRoleIds.getRoleIds();
		if (roleIds == null || roleIds.length == 0) {
			throw new IllegalArgumentException("必须为用户分配角色");
		}
		// uuid作为盐值
		String salt = UUID.randomUUID().toString();
		/*
		 * md5加密，只能加密不能解密，相同内容加密结果是一样的， 所以得把salt保存，每次把密码和盐值加密和数据库中密码比较，
		 * 数据库中密码是原密码经过salt加密后的密码
		 */
		// shiro框架给密码加密
		SimpleHash sh = new SimpleHash("md5", // algorithmName算法名称
				sourcePassword, // source原密码
				salt, // salt盐值
				1// iterations加密此数，可以对加密结果进行再加密
		);
		// 把加密过的密码和盐值存入数据库
		userDeptRoleIds.setPassword(sh.toHex());
		userDeptRoleIds.setSalt(salt);
		Users user = ShiroUtils.getUser();
		userDeptRoleIds.setCreatedUser(user.getUsername());
		userDeptRoleIds.setModifiedUser(user.getUsername());
//		System.out.println(userDeptRoleIds);
		int rows = usersDao.insertUser(userDeptRoleIds);
//		System.out.println(rows);
		int rolesRows = usersRolesDao.insertRoles(userDeptRoleIds);
		return rows;
	}

	@Cacheable(value = "usersCache")
	@Override
	public PageObject<UserDeptRoleIds> findPageObject(String username, Integer pageCurrent) {
		// 测试业务层缓存生效没
//		System.out.println("findPageObject");
		// 1.对参数进行校验
		if (pageCurrent == null || pageCurrent < 1) {
			// 运行时异常
			throw new IllegalArgumentException("当前页码值无效");
		}
		// 2.查询记录总数，校验
		int rowCount = usersDao.getRowCount(username);
		if (rowCount == 0) {
			// 业务层异常
			throw new ServiceException("没有找到对应记录");
		}
		// 3.查询当前页记录
		int pageSize = 2;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<UserDeptRoleIds> records = usersDao.findPageObjects(username, startIndex, pageSize);
		// 4.查询结果封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

	// 告诉shiro授权检测，访问方法需要"sys:user:valid"权限
	/*
	 * 授权检测步骤： 1."sys:user:valid"提交给shiro框架 2.基于用户找到用户的权限信息
	 */
	@CacheEvict(value = "usersCache", allEntries = true)
	@RequiresPermissions("sys:user:valid")
	@RequiredLog("禁用启用")
	@Override
	public int updateValidByUserId(Users users) {
		int usersId = users.getId();
		if (usersId < 1) {
			throw new IllegalArgumentException("id值无效");
		}
		int rows = usersDao.updateValidByUserId(users);
		return rows;
	}

	// 更新缓存，缓存selectById时
	// spEL表达式 #userDeptRoleIds.id，更新指定缓存
//	@CachePut(value = "usersCache",key = "#userDeptRoleIds.id")
	// 删除缓存，缓存selectAll时
	// beforeInvocation = false默认更新成功后再清缓存
	// allEntries = true，删除指定value的所有缓存
	@CacheEvict(value = "usersCache", allEntries = true)
	@RequiredLog("修改用户")
	@Override
	public int updateUserDeptRoleIdsById(UserDeptRoleIds userDeptRoleIds) {
		int usersId = userDeptRoleIds.getId();
		if (usersId < 1) {
			throw new IllegalArgumentException("id值无效");
		}
		int rows = usersDao.updateInfo(userDeptRoleIds);
		usersRolesDao.deleteObjectsByUserId(usersId);
		usersRolesDao.insertRoles(userDeptRoleIds);
		return rows;
	}

	@Override
	public int editPassword(EditPassword editPassword) {
		String oldPassword = editPassword.getOldPassword();
		String newPassword = editPassword.getNewPassword();
		String newConfirm = editPassword.getNewConfirm();
		if(StringUtils.isEmpty(oldPassword)) {
			throw new IllegalArgumentException("原密码不能为空");
		}
//		System.out.println(newPassword);
//		System.out.println(newConfirm);
		if(StringUtils.isEmpty(newPassword)) {
			throw new IllegalArgumentException("新密码不能为空");
		}
		if(!newPassword.equals(newConfirm)) {
			throw new IllegalArgumentException("密码输入不一致");
		}
		Users user = (Users)SecurityUtils.getSubject().getPrincipal();		
		SimpleHash sh = new SimpleHash("md5", // algorithmName算法名称
				oldPassword, // source原密码
				user.getSalt(), // salt盐值
				1// iterations加密此数，可以对加密结果进行再加密
				);
		//登录用户的加密密码 和  输入的旧密码取登录用户的盐值加密后 对比
		if(!user.getPassword().equals(sh.toHex())) {
			throw new ServiceException("旧密码错误");
		}
		sh = new SimpleHash("md5", // algorithmName算法名称
				newPassword, // source原密码
				user.getSalt(), // salt盐值
				1// iterations加密此数，可以对加密结果进行再加密
				);
		if(user.getPassword().equals(sh.toHex())) {
			throw new ServiceException("新密码不能和原密码相同");
		}
		String salt = UUID.randomUUID().toString();
		/*
		 * md5加密，只能加密不能解密，相同内容加密结果是一样的， 所以得把salt保存，每次把密码和盐值加密和数据库中密码比较，
		 * 数据库中密码是原密码经过salt加密后的密码
		 */
		// shiro框架给密码加密
		sh = new SimpleHash("md5", // algorithmName算法名称
				newPassword, // source原密码
				salt, // salt盐值
				1// iterations加密此数，可以对加密结果进行再加密
		);
		// 把加密过的密码和盐值存入数据库
		user.setPassword(sh.toHex());
		user.setSalt(salt);
		int rows = usersDao.editPassword(user);
		if(rows==0) {
			throw new ServiceException("记录已经不存在");
		}
		return rows;
	}

	@Override
	public void doLogin(LoginVo loginVo) {
		// 1.封装用户信息
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(loginVo.getUsername());
		/*
		 * 需要char[]，点开token.setPassword(String)， 查看系统转化的方式
		 */
		token.setPassword(loginVo.getPassword().toCharArray());
		// 2.提交用户信息（借助Subject对象）
		Subject subject = SecurityUtils.getSubject();
		//设置shiro记住
		if(loginVo.getIsRemember()) {
			token.setRememberMe(true);
		}
		// 提交用户信息进行认证
		subject.login(token);
	}
}
