package com.project.util;
import com.project.entity.UserEntity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 自定义Realm类
 */
public class CustomRealm extends AuthorizingRealm {
    /**执行授权逻辑，把权限交给shiro*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行授权逻辑");

       /* //已经登录成功
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //用用户名 去数据库中查询出该用户的所有权限，交给shiro

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("finduser");
        simpleAuthorizationInfo.addStringPermission("finduser2");
        simpleAuthorizationInfo.addStringPermission("finduser3");
        return simpleAuthorizationInfo;*/

       return null;
    }


    /**执行认证逻辑，把用户交给shiro*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行认证逻辑");
        /*

        //在token中获取到登录的用户名和密码
        String userName = authenticationToken.getPrincipal().toString();
        char[] charpass =   ( (UsernamePasswordToken) authenticationToken).getPassword();
        String userPass =new String(charpass,0,charpass.length);

        //去数据库中查询用户是否存在
        UserEntity user=new UserEntity();
        user.setUserName(userName);
        user.setUserPass(userPass);
        if (user == null) {
            //在数据库中没有查询到用户，登录失败
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPass(), getName());
            return simpleAuthenticationInfo;
        }*/

        return null;

    }
}
