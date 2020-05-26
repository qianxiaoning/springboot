package com.qxn.pj.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qxn.pj.sys.dao.MenusDao;
import com.qxn.pj.sys.dao.RolesMenusDao;
import com.qxn.pj.sys.dao.UsersDao;
import com.qxn.pj.sys.dao.UsersRolesDao;
import com.qxn.pj.sys.entity.Users;
@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private UsersRolesDao usersRolesDao;
	@Autowired
	private RolesMenusDao rolesMenusDao;
	@Autowired
	private MenusDao menusDao;
	//getCredentialsMatcher和setCredentialsMatcher都可以
//	public CredentialsMatcher getCredentialsMatcher() {
//		//构建凭证匹配器对象
//		HashedCredentialsMatcher cMatcher = 
//				new HashedCredentialsMatcher();
//		//指定算法名称
//		cMatcher.setHashAlgorithmName("MD5");
//		//加密次数
//		cMatcher.setHashIterations(1);
//		return cMatcher;
//	}
	//设置凭证匹配器，指定加密算法，交给认证管理器
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配器对象
		HashedCredentialsMatcher cMatcher = 
				new HashedCredentialsMatcher();
		//指定算法名称
		cMatcher.setHashAlgorithmName("MD5");
		//加密次数
		cMatcher.setHashIterations(1);
		//传递凭证匹配器至认证管理器
		super.setCredentialsMatcher(cMatcher);
	}
	@Override
	//认证信息的获取及封装
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("=====认证中=====");
		//1.从token(令牌)获取用户名
		UsernamePasswordToken uPToken = 
				(UsernamePasswordToken)token;
		String username = uPToken.getUsername();
		//2.基于用户名查询用户对象
		Users user = usersDao.findUsersByUserName(username);
		//3.判断用户是否存在
		if(user==null) {
			throw new UnknownAccountException();
		}
		//4.判断用户是否被禁用
		if(user.getValid()==0) {
			throw new LockedAccountException();
		}
		//5.封装用户信息并返回，传递给认证管理器进行认证
		//SimpleAuthenticationInfo认证的info对象
		/*写完参数报错，点进SimpleAuthenticationInfo，ctrl+o，
		 * 看方法的参数类型，第三个参数是ByteSource
		 * */
		ByteSource credentialsSalt = 
				ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(
						user,//principal身份
						user.getPassword(),//hashedCredentials已加密的凭证信息
						credentialsSalt,//credentialsSalt盐值
						this.getName());//realmName名，"ShiroUserRealm" 
		return info;//交给认证管理器
	}
		
		
	@Override
	//授权信息的获取及封装
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("=====授权中=====");
		//查询方法：1.多次单表查询，2.一次多表查询
		//1.获取登录用户信息
		Users user = (Users)principals.getPrimaryPrincipal();
		//2.基于用户id，查询用户角色id（可能是多个）
		List<Integer> roleIds = usersRolesDao.findRoleIdsByUsersId(user.getId());
		if(roleIds==null||roleIds.size()==0) {
			throw new AuthorizationException();
		}
		//3.基于用户角色id，查询菜单id（可能是多个）
		Integer[] array = {};
//		System.out.println(roleIds);
		List<Integer> menuIds = rolesMenusDao.selectMenusIdsByRoleIds(roleIds.toArray(array));
		if(menuIds==null||menuIds.size()==0) {
			throw new AuthorizationException();
		}
		//4.查询菜单id，获取菜单权限标识
		List<String> permissions = menusDao.findPermissionsByMenuIds(menuIds.toArray(array));
		if(permissions==null||permissions.size()==0) {
			throw new AuthorizationException();
		}
		//5.封装查询结果返回给 授权管理器
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//要用Set，对重复的权限去重
		Set<String> permissionsSet = new HashSet<>();
		for (String p : permissions) {
			if(!StringUtils.isEmpty(p)) {
				permissionsSet.add(p);
			}
		}
		info.setStringPermissions(permissionsSet);
		return info;
	}
}
