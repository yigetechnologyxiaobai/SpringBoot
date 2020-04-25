package com.cyy.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(manager);

        /*
            anno：无需认证就可以访问
            authc：必须认证了才能访问
            user：必须拥有 记住我 功能才能访问
            perms：拥有对某个资源的权限才能访问
            role：拥有某个角色资源才能访问
         */

        //登录拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/user/add", "authc");
        filterMap.put("/user/update", "authc");

        factoryBean.setFilterChainDefinitionMap(filterMap);

        factoryBean.setLoginUrl("/toLogin");

        return factoryBean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建 realm 对象
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
