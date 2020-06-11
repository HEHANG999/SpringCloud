package com.project.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
public class TwoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedissonClient redisson;


    @RequestMapping("addTwo/{num}")
    public int addTwo(@PathVariable("num")int num){

        RLock rLock= redisson.getLock("resocueLock");

        boolean bl=false;
        try {
            //尝试加锁，最多等待100秒，上锁以后30秒自动解锁
            bl = rLock.tryLock(100,30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(bl);

        if(bl){
            String url = "http://127.0.0.1:8010/add/"+num;

            ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);

            return Integer.parseInt(responseEntity.getBody().toString());
        }

        return 0;
    }


}
