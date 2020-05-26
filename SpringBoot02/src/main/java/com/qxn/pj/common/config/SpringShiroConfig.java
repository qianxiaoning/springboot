package com.qxn.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringShiroConfig {
	/**
	 *添加shiro缓存管理器对象，cacheManager，
	 *对用户权限信息进行缓存操作，ctrl+shift+t，
	 *看见CacheManager是接口，ctrl+t看实现类
	 *MemoryConstrainedCacheManager
	 */
	public CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	//配置rememberMe
	public CookieRememberMeManager cookieRememberMeManager() {
		CookieRememberMeManager cManager = 
				new CookieRememberMeManager();
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		/*设置cookie失效时间，默认expires为session，浏览器关闭，
		 * 会话结束就销毁了
		 * */
		//rememberMe这个cookie过期时间是7天
		cookie.setMaxAge(7*24*60*60);
		cManager.setCookie(cookie);
		return cManager;
	}
	//session管理器对象，用户登录成功后，用户对象就会存入session中
	public SessionManager sessionManager() {
		DefaultWebSessionManager sManager = 
				new DefaultWebSessionManager();
		SimpleCookie sC = new SimpleCookie();
		//30分钟
		sManager.setGlobalSessionTimeout(30*60*1000);
		return sManager;
	}
	@Bean // 默认key为方法名
	// shiro中核心对象，认证授权
	public SecurityManager securityManager(Realm realm) {
		/*
		 * SecurityManager是接口，打开它，ctrl+t看继承关系， 找到能够实例化的子类，上面都是抽象类
		 */
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		//将缓存管理器注入securityManager对象
		sManager.setCacheManager(cacheManager());
		sManager.setRememberMeManager(cookieRememberMeManager());
		sManager.setSessionManager(sessionManager());
		return sManager;
	}
	
	@Bean
	// 作用创建ShiroFilter过滤器
	// 将SecurityManager注入进来
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		// 创建过滤器工厂
		ShiroFilterFactoryBean fBean = new ShiroFilterFactoryBean();
		// 注入setSecurityManager
		fBean.setSecurityManager(securityManager);
		/*
		 * 设置登录url，setLoginUrl是redirect操作， 基于安全考虑，Response Header的信息会被清除，
		 * 导致cors和前端proxy跨域， nginx仍然有效
		 */
		// 未写setLoginUrl，默认跳转login.jsp		
		fBean.setLoginUrl("/users/returnToLogin");
		// 设置过滤规则
		// 下面map用，LinkedHashMap，保证key的添加顺序
		LinkedHashMap<String, String> cMap = new LinkedHashMap<>();
		// 某些静态资源，anon允许匿名访问，不进行登录认证，直接放行
		cMap.put("/some_static/**", "anon");
		cMap.put("/users/doLogin", "anon");
		cMap.put("/users/doLoginOut", "logout");
		// 剩下的资源认证
//		cMap.put("/**", "authc");
		//记住我开启
		cMap.put("/**", "user");
		fBean.setFilterChainDefinitionMap(cMap);
		return fBean;
	}

	// 授权配置

	/*
	 * shiro中bean的生命周期管理器，需要特殊的管理方式，按照特定顺序回收 按照spring标准继承spring接口重写此方法。
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	// 配置代理对象创建器，为目标业务对象，创建代理对象
	/*
	 * 此对象在spring启动时，扫描所有Advisor（通知）对象， 对指定切入点（Pointcut）的类，创建代理对象
	 */
	/*
	 * 此对象创建的对象， 其生命周期给lifecycleBeanPostProcessor管理
	 */
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	/*
	 * 配置Advisor对象，定义切入点，配置在切入点上的功能扩展(advice)
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
