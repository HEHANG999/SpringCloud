package com.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 这是一个客户端，用于RPC远程调用另一个服务器
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})//表示springboot
@EnableEurekaClient//注册客户端（微服务）
@EnableFeignClients//cloud提供的feign远程调用
public class ConfigClient2Main {


    public static void main(String[] args) {

        SpringApplication.run(ConfigClient2Main.class);//表示启动本服务器
    }

}
