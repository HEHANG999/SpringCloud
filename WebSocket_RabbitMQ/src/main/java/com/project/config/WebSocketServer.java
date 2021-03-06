package com.project.config;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket服务端
 */

@Slf4j
@ServerEndpoint("/websocket")//把当前类标识成一个WebSocket的服务端，值是访问的URL地址
@Component//spring注入
public class WebSocketServer {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //存放session的集合，很重要！！
    private static CopyOnWriteArraySet<WebSocketServer> sessions = new CopyOnWriteArraySet<WebSocketServer>();

    //用于存所有的连接服务的客户端，这个对象存储是安全的（因为HashMap不支持线程同步）
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();


    //从队列中取出来
    @RabbitListener(queues = "pointQueue")
    public void getMessAge(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {

        System.out.println("发来消息：" + message);

        channel.basicAck(tag, false);//确认收到--消费

        //用来判断session中是否存在数据
        if (sessions.size() != 0) {
            for (WebSocketServer s : sessions) {
                if (s != null) {
                    s.session.getBasicRemote().sendText(message);//向已连接客户端发送信息
                }
            }
        }
    }


    //连接成功
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //这个一定要写，第一次很容易忽略！
        sessions.add(this);
        log.info("[WebSocket] 连接成功，当前连接人数为：={}", webSocketSet.size());

    }

    //连接断开
    @OnClose
    public void onClose() {
        //释放
        sessions.remove(this);
        log.info("[WebSocket] 退出成功，当前连接人数为：={}", webSocketSet.size());
    }

    //收到消息
    @OnMessage
    public String onMessage(String message) {
        log.info("[WebSocket] 收到消息：{}", message);

        //这里是自己测试的
        if (message.equals("一只羊")) {//不能用==
            return "1:1个大白羊";
        }
        if (message.equals("两只羊")) {
            return "2:2个大白羊";
        }
        if (message.equals("三只羊")) {
            return "3:个大白羊";
        }

        return "你已成功连接，这是webSocket服务端的返回信息！";
    }











   /* public void goHtml(String str) {//直接连接一个服务器
        HttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost("http://127.0.0.1:7777/sendWeb?str="+str);

        try {
            client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
