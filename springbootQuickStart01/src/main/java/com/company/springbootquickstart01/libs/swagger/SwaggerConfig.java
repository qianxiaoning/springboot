package com.company.springbootquickstart01.libs.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        //添加head参数配置start
        ParameterBuilder scenePar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        scenePar.name("scene").description("场景").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(scenePar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company.springbootquickstart01"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(
                        new ApiInfoBuilder()
                        .title("springbootQuickStart01")
                        .description("这是一个springbootQuickStart01")
                        .version("1.0.0")
                        .contact(new Contact("company","https://www.baidu.com/","xx@qq.com"))
                        .license("A License")
                        .licenseUrl("https://www.baidu.com/")
                        .build()
                );
    }
}