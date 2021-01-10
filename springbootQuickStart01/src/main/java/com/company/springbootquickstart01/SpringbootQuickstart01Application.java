package com.company.springbootquickstart01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.company.springbootquickstart01.dao")
public class SpringbootQuickstart01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuickstart01Application.class, args);
    }

}
