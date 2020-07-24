package com.qxn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//common项目中包含数据库驱动包，加载包需要配置数据源
//表示启动时不需要数据源，不需要配置yml文件，spring.datasource
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class C2cSystemWebWarApplication {
    public static void main(String[] args) {
        SpringApplication.run(C2cSystemWebWarApplication.class,args);
    }
}
