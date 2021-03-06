package com.project.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//不做视图渲染，只返回纯数据
public class Client1Controller {

//-----------------------------get请求---------------------------------------------------------------------------

    @RequestMapping("client1Info/{tag}")   //restFull风格的传参形式，将url上的tag参数，@PathVariable赋值给字符串param
    @HystrixCommand(fallbackMethod = "error")//熔断指引
    public String client1Info(@PathVariable("tag") String param){

        //int i = 9/0;
        switch (param){
            case "1":
                return "这个参数是数字："+param;
            case "2":
                return "这个参数是数字："+param;
            default:
                return "你写的乱七八糟："+param;
        }

    };






//-----------------------------熔断---------------------------------------------------------------------------


    public String error(String param){
        return "出错了。";
    }


}
