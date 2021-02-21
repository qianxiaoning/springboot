package com.company.springbootquickstart01.codes.common.filter;

import com.company.springbootquickstart01.libs.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FilterRegistrationConfig {
    @Autowired
    private FilterA filterA;
    @Bean
    public FilterRegistrationBean registFilter() {
//        System.out.println("FilterA config======");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(filterA);
        //设置过滤器name属性
        registration.setName("filterA");
        //setUrlPatterns设置拦截规则，⽀持正则，在此添加需要过滤的路径
        List<String> urls = new ArrayList<>();
        urls.add("/order/*");
        urls.add("/user/*");
        registration.setUrlPatterns(urls);
        //设置过滤器初始化参数
        Map<String, Object> map = new HashMap<>();
        map.put("character","UTF-8");
        registration.setInitParameters(map);
        //过滤器init方法里的filterConfig获取到配置信息
        //过滤器doFilter方法的执行顺序，值越小越优先，默认是Bean的加载顺序
        registration.setOrder(3);
        return registration;
    }
    @Bean
    public FilterRegistrationBean registFilter2() {
//        System.out.println("FilterB config======");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FilterB());
        registration.setOrder(1);
        return registration;
    }
}
