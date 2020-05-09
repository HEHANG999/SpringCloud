package com.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 这是一个配置服务器，用于方便管理主服务器下，其它子服务器的配置
 */
@SpringBootApplication
@EnableConfigServer//配置服务器
public class ConfigMain {

    public static void main(String[] args) {

        SpringApplication.run(ConfigMain.class);
    }
}
