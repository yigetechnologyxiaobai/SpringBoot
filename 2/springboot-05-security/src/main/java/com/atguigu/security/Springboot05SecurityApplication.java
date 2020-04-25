package com.atguigu.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、引入SpringSecurity模块
 * 2、编写SpringSecurity的配置类
 *      @EnableWebSecurity extends WebSecurityConfigurerAdapter
 * 3、控制请求的访问去哪先
 */
@SpringBootApplication
public class Springboot05SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05SecurityApplication.class, args);
    }

}
