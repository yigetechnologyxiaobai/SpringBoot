package com.cyy.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//AOP：拦截器
@EnableWebSecurity
public class RoleConfig extends WebSecurityConfigurerAdapter {

    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有对应有权限的人才能访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //订制登录页
        //username和password的参数需要对应传的name
        http.formLogin().loginPage("/toLogin").usernameParameter("username").passwordParameter("pwd");

        //没有权限默认会到登录页面，需要开启登录的页面
        http.formLogin().successForwardUrl("/index");

        //开启，注销功能，跳到首页
        http.csrf().disable();      //关闭crsf功能
        http.logout().logoutSuccessUrl("/index");

        //开启记住我功能，cookie默认保存两周，自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember");

    }


    //认证


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //从内存中获取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("cyy").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3").and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3");

    }
}
