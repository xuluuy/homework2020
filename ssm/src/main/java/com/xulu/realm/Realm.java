package com.xulu.realm;


import com.xulu.model.Staff;
import com.xulu.model.User;

import com.xulu.service.StaffService;

import com.xulu.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuluuy
 * @create 2020-01-16 17:53
 */
public class Realm extends AuthorizingRealm{

    @Autowired
    private UserService userService;
    // 简单重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取页面封装的用户信息 id
        String u_username = token.getPrincipal().toString();
        //2.根据用户名 获取用户信息
        User user = userService.getByUserName(u_username);
        //查询表 获取用户的密码、盐值
        String passwordInDB = user.getU_password();
        String salt = user.getU_salt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(u_username, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}




