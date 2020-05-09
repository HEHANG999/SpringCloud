package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 演示负载均衡
 */
@SpringBootApplication//表示springboot
@EnableEurekaClient//注册客户端（微服务）
public class Client1sMain {


    public static void main(String[] args) {

        SpringApplication.run(Client1sMain.class);//表示启动本服务器
    }

}
