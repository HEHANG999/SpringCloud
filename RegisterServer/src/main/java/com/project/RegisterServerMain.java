package com.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 这是注册服务器，所以子服务器都向这里注册
 */
@SpringBootApplication
@EnableEurekaServer//注册服务器
public class RegisterServerMain {

    public static void main(String[] args) {

        SpringApplication.run(RegisterServerMain.class);
    }
}
