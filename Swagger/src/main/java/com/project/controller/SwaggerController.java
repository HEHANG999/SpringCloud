package com.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "个人测试swagger")
@RestController
public class SwaggerController {

    @ApiOperation(value = "返回hello+",notes = "restFull风格")
    @GetMapping("hello/{tag}")
    public String hello(@PathVariable("tag") String tag){

        return "hello"+tag;
    }


    @ApiOperation(value = "登录",notes = "post方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName",value = "用户名",type = "String"),
                        @ApiImplicitParam(name = "password",value = "密码",type = "String")})
    @PostMapping("post")
    public String post(String userName,String password){

        return userName+";"+password;
    }


   /* @RequestMapping("reqAll")
    public  String reqAll(){
        return "hello";
    }*/


}
