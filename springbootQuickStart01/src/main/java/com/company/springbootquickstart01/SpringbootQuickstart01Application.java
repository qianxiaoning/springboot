package com.company.springbootquickstart01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.company.springbootquickstart01.codes.dao")
@ServletComponentScan("com.company.springbootquickstart01.codes.common")
//使用@ServletComponentScan注解后，
//Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码
public class SpringbootQuickstart01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuickstart01Application.class, args);
    }

}
