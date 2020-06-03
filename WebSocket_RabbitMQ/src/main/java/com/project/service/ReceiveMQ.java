package com.project.service;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

/**
 * MQ接收类
 */
@Service("ReceiveMQ")
public class ReceiveMQ {


    //这个写在webSocket里面用于监听队列消息
   /* @RabbitListener(queues = "pointQueue")//监听器，监听哪一个队列（心跳程序）
    public void getQueueAInfo(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        //消费消息---手动
        try {
            //tag表示唯一标志从队列中获取，然后放回去
            //false表示接收当前队列消息，true表示接收多个队列消息
            channel.basicAck(tag, false);//确认收到
            System.out.println("收到队列发来消息：" + message);
            }
        catch (IOException e) {
            try {
                //发生IO流异常，重新到队列中取（tag标志、b1单个队列、b2=false删除队列数据，=true不删除）
                channel.basicNack(tag, false, true);//确认没收到---反复取
                System.out.println("收到队列发来消息2：" + message);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }*/


    @RabbitListener(queues = "topic.pointQueue.a")//监听器，监听哪一个队列（心跳程序）
    public void getTopicQueueAInfo(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("收到队列发来消息："+message);

        //消费消息
        channel.basicAck(tag, false);
    }


    @RabbitListener(queues = "dealQueue")//监听死信队列
    public void getDealQueueInfo(String message){

        System.out.println("收到死信队列发来消息："+message);
    }


    @RabbitListener(queues = "dealToQueue")//监听已绑定死信的队列
    public void getDealToQueueInfo(String message,Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){

        System.out.println("收到队列发来消息2："+message);

        try {
            channel.basicNack(tag,false,true);//手动不确认收到消息，会反复取(直到6秒后停止)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
