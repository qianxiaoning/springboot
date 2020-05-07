package com.qxn.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringShiroConfig {
	@Bean//默认key为方法名
	//shiro中核心对象，认证授权
	public SecurityManager securityManager() {
		/*SecurityManager是接口，打开它，ctrl+t看继承关系，
		 * 找到能够实例化的子类，上面都是抽象类
		 * */
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		return sManager;
	}
	
	@Bean
	//作用创建ShiroFilter过滤器
	//将SecurityManager注入进来
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		//创建过滤器工厂
		ShiroFilterFactoryBean fBean = new ShiroFilterFactoryBean();
		//注入setSecurityManager
		fBean.setSecurityManager(securityManager);
		/*设置登录url，setLoginUrl是redirect操作，基于安全考虑，Response Header的信息会被清除,导致跨域
		 * */
		fBean.setLoginUrl("/doLogin");
		//设置过滤规则
		//下面map用，LinkedHashMap，保证key的添加顺序
		LinkedHashMap<String, String> cMap = new LinkedHashMap<>();
		//某些静态资源，anon允许匿名访问
		cMap.put("/some_static/**","anon");
		//剩下的资源认证
		cMap.put("/**","authc");
		fBean.setFilterChainDefinitionMap(cMap);
		return fBean; 
	}
}
