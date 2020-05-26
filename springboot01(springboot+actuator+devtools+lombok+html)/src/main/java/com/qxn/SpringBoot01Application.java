package com.qxn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * springboot启动类
 * 1.基于注解(@SpringBootApplication)描述实现自动化配置
 *2. 一个项目一个启动类
 *3.系统启动时自动扫描本类所在包以及子包中的对象
 *4.读取classpath中的配置文件，例如application.properties,application.yml
 * */
@SpringBootApplication
public class SpringBoot01Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot01Application.class, args);
	}

}
