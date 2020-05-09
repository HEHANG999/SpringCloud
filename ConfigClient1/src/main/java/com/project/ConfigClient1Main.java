package com.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 这是一个客户端
 */
@SpringBootApplication//表示springboot
@EnableEurekaClient//注册客户端（微服务）
public class ConfigClient1Main {


    public static void main(String[] args) {

        SpringApplication.run(ConfigClient1Main.class);//表示启动本服务器
    }

}
