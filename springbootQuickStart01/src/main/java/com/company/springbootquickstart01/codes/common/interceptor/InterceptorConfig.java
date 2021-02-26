package com.company.springbootquickstart01.codes.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private TimeAllowedInterceptor timeAllowedInterceptor;
    @Autowired
    private LoginedValidateInterceptor loginedValidateInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] swaggerExcludes =
                new String[]{
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/",
                        "/error",
                        "/csrf"
                };
        //允许访问时间拦截器
        registry.addInterceptor(timeAllowedInterceptor)
                //拦截路径
                .addPathPatterns("/**")
                .excludePathPatterns(swaggerExcludes)
        ;
        //登录验证拦截器
        String[] serviceExcludes =
                new String[]{
                        "/api/user/login",
                        "/api/user/register",
                };
        registry.addInterceptor(loginedValidateInterceptor)
                //拦截路径
                .addPathPatterns("/**")
                .excludePathPatterns(swaggerExcludes)
                .excludePathPatterns(serviceExcludes)
        ;
    }
}
