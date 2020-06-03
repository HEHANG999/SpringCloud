package com.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 这是一个客户端
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})//头部声名：表示这个springboot不需要数据源配置！！
@EnableEurekaClient//注册客户端（微服务）
@EnableCircuitBreaker//熔断机制！！------出错后的指定（防止雪崩）
public class Client1Main {


    public static void main(String[] args) {

        SpringApplication.run(Client1Main.class);//表示启动本服务器
    }

}
