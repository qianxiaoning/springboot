package com.company.springbootquickstart01.codes.common.listener.systemListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServletListenerRegistrationConfig {
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        //注册监听器
        servletListenerRegistrationBean.setListener(new MyServletContextListener());
        return servletListenerRegistrationBean;
    }
}
