package com.cyy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class MyConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource source() {
        return new DruidDataSource();
    }

    //主要实现web监控的配置处理
    @Bean
    public ServletRegistrationBean statViewServlet() {

        //进行druid监控的配置处理操作
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要登录，账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();

        //增加配置
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");

        // 白名单,多个用逗号分割， 如果allow没有配置或者为空，则允许所有访问
        initParameters.put("allow", "");

        // 黑名单,多个用逗号分割 (共同存在时，deny优先于allow)
        initParameters.put("deny", "192.168.12.15");

        bean.setInitParameters(initParameters);
        return bean;

    }


    //filter
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();

        bean.setFilter(new WebStatFilter());
        //可以过滤请求
        HashMap<Object, Object> initParameters = new HashMap<>();
        //不过滤的请求
        initParameters.put("exclusions", "*.js,*.css,/druid/*,/firstServlet");
        bean.setInitParameters(initParameters);

        return bean;
    }


}
