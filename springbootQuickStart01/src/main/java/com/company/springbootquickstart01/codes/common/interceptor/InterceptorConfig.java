package com.company.springbootquickstart01.codes.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private TimeAllowedInterceptor timeAllowedInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeAllowedInterceptor)
                //拦截路径
                .addPathPatterns("/**")
//                .excludePathPatterns("/v2/api-docs-ext")
        ;
//        registry.addInterceptor(new AInterceptor2());
    }
}
