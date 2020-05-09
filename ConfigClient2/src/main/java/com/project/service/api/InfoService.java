package com.project.service.api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ConfigClientOne")//去该服务器访问
public interface InfoService {

    @RequestMapping("/client1Info/{tag}")//调用Client1的方法
    public String infoTo(@PathVariable("tag") String param);//这只是client2的一个接口
}
