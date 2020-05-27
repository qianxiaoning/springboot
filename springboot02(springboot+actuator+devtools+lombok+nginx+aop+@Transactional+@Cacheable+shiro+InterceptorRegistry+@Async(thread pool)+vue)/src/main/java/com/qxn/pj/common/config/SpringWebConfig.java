package com.qxn.pj.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.qxn.pj.common.web.TimeAccessInterceptor;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{
	//TimeAccessInterceptor如果用多次，可以交给spring管理，只用一次就不用了
//	@Autowired
//	private TimeAccessInterceptor interceptor;
//	registry.addInterceptor(interceptor)
	//添加拦截器，设置拦截路径
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TimeAccessInterceptor())
		//放行路径
//		.excludePathPatterns(patterns)
		//多个拦截路径
//		.addPathPatterns("/users/*");
		//拦截路径
		.addPathPatterns("/users/doLogin");
	}
	
	/*这个可以不要了，
	 * springboot内置了FilterRegistrationBean这个对象
	 * */
	//注册过滤器对象
	//@Bean
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
