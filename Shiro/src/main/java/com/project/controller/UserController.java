package com.project.controller;
import com.project.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    //登录
    @RequestMapping("loginUser")
    public String loginUser(UserEntity user){
        System.out.println(user.toString());

        //添加用户认证信息
        //获得shiro主题
        Subject subject = SecurityUtils.getSubject();
        //把我们的用户和密码放入到shiro的token对象中
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getUserPass()
        );

        //进行验证，这里可以捕获异常，然后返回对应信息
        try{
            subject.login(usernamePasswordToken);
        }catch (Exception e){
            return "登录失败";
        }

        return "登录成功";
    }










    //查看权限

    @GetMapping("findUser")
    public String findUser(){
        //认证
        boolean b = SecurityUtils.getSubject().isPermitted("findUSer");
        if (!b){
            return "no power";
        }
        return "hello";
    }

    @GetMapping("findUser1")
    public String findUser1(){
        //认证
        boolean b = SecurityUtils.getSubject().isPermitted("findUSer1");
        if (!b){
            return "no power1";
        }
        return "hello1";
    }

    @GetMapping("findUser2")
    public String findUser2(){
        //认证
        boolean b = SecurityUtils.getSubject().isPermitted("findUSer2");
        if (!b){
            return "no power2";
        }
        return "hello2";
    }



}
