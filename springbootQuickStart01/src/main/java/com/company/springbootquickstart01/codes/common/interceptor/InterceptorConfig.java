package com.company.springbootquickstart01.codes.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AInterceptor())
                //拦截路径
                .addPathPatterns("/**")
//                .excludePathPatterns("/v2/api-docs-ext")
        ;
    }
}
