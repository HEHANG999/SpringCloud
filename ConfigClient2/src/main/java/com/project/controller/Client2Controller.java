package com.project.controller;
import com.project.service.api.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client2Controller {

    @Autowired
    private InfoService infoService;


//-----------------------------get请求---------------------------------------------------------------------------


    @RequestMapping("client2Info")   //普通请求-----client2Info?param=666的形式
    public String client2Info1(String param){
        System.out.println(param);
        String str = infoService.infoTo(param);//调用本地接口进行远程调用
        return str;
    }

}
