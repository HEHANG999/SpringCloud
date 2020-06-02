package com.project.controller;
import com.project.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证用户
 */
@RestController
public class UserController {


    /**登录*/
    @PostMapping("login")
    public String login(String userName,String userPass){

        String token = "";

        if (userName.equals("张三") && userPass.equals("000")){
            //登录成功，返回token
            token = JwtUtil.sign(userName, userPass);
        }
        //登录失败
        return token;
    }

}
