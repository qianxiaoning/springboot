package com.qxn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.qxn.mapper")
public class C2cSystemSsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(C2cSystemSsoApplication.class,args);
    }
}
