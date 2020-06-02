package com.project.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 */
@RestController
public class UserController {



    @GetMapping("userInfo/{tag}")//result风格
    public String userinfo(@PathVariable("tag") String tag){

        String info ="";

        if (tag.equals("1")){
            info="hello";
        }else if (tag.equals("2")){
            info="world";
        }

        return info;
    }
}
