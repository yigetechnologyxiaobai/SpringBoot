package com.cyy.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法=>principalCollection");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证方法=>authenticationToken");

        //用户名、密码    数据库中取
        String name = "root";
        String password = "123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        if (!userToken.getUsername().equals(name)) {
            return null;        //异常抛出  UnknownAccountException
        }

        //密码认证，shiro做~
        return new SimpleAuthenticationInfo("", password, "");
    }
}
