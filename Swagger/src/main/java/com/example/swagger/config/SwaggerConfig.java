package com.example.swagger.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static javax.xml.transform.OutputKeys.VERSION;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket docket(Environment enviroment){



        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");
        //通过acceptProfile判断是否处在自己设定的环境当中
        boolean flag = enviroment.acceptsProfiles(profiles);
        flag = true;

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                //配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title( "Dr森的Swagger学习文档")
                .description("加油学习")
                .build();

    }

//    public static final String VERSION = "1.0.0";
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("茉莉机器人接口")
//                .description("机器人后台请求接口")
//                .contact(new Contact("茉莉机器人", "", "chatbot@itpk.cn"))
//                .version(VERSION)
//                .build();
//    }
}
