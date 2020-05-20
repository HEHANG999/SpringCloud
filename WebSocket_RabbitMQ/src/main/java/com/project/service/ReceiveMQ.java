package com.project.service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

/**
 * MQ接收类
 */
@Service("ReceiveMQ")
public class ReceiveMQ {



    @RabbitListener(queues = "pointQueue")//监听器，监听哪一个队列（心跳程序）
    public void getQueueAInfo(String message) throws Exception {


        System.out.println("收到队列发来消息："+message);
    }


    @RabbitListener(queues = "topic.pointQueue.a")//监听器，监听哪一个队列（心跳程序）
    public void getTopicQueueAInfo(String message){
        System.out.println("收到队列发来消息："+message);
    }


}
