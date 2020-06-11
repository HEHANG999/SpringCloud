package com.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 演示分布式锁
 */
@SpringBootApplication
public class ResourceMain {

    public static void main(String[] args) {
        SpringApplication.run(ResourceMain.class);
    }
}
