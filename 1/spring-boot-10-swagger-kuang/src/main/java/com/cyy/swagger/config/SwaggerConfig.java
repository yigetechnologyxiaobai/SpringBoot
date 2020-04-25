package com.cyy.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2     //开启 Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    @Bean
    public Docket getDocket(Environment environment){

        //设置要显示的 Swagger 环境
        Profiles profiles = Profiles.of("dev", "test");

        //通过 environment.acceptsProfiles 判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                //enable是否启动Swagger，如果为false，则Swagger不能在浏览器中访问
                .enable(flag)
                .groupName("cyy")
                .select()
                /*
                    RequestHandlerSelectors，配置要扫描接口的方式
                    basePackage：指定要扫描的包
                    any()：扫描全部
                    none()：不扫描
                    withClassAnnotation：扫描类上的注解，参数是一个注解的反射对象
                    withMethodAnnotation：扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.basePackage("com.cyy.swagger.controller"))
                //paths()：过滤什么路径
                .paths(PathSelectors.ant("/cyy/*"))
                .build();
    }

    private ApiInfo getApiInfo(){

        //作者信息
        Contact contact = new Contact("cyy","localhost:8080/hello","576779239@qq.com");

        return new ApiInfo(
                "Cyy的API",
                "一直有梦想的咸鱼",
                "1.1",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
