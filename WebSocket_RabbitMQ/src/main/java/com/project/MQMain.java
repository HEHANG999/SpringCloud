package com.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 必须先开启rabbitMQ！！
 */
@SpringBootApplication
public class MQMain {

    public static void main(String[] args) {
        SpringApplication.run(MQMain.class);
    }
}
