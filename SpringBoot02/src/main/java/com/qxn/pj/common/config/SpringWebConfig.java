package com.qxn.pj.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class SpringWebConfig {
	//注册过滤器对象
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean(){
		FilterRegistrationBean<DelegatingFilterProxy> rBean
		= new FilterRegistrationBean<>();
		//添加过滤器不是add就是set
		/*DelegatingFilterProxy是spring中的过滤器
		 * */
		rBean.setFilter(
				new DelegatingFilterProxy(
						"shiroFilterFactoryBean"));
		//过滤所有请求
		rBean.addUrlPatterns("/*");
		return rBean;
	}
}
