package com.project.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.HashMap;
import java.util.Map;

/**
 * MQ配置类
 */
@Configuration//spring配置类
public class MQConfig {

    //1、注入工厂（已经预加载，直接注入）
    @Autowired
    CachingConnectionFactory factory;

    //2、rabbitMQ模板
    @Bean(value = "rabbit")//交给spring容器管理
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(factory);
    }

//=================================================================================================


    //3、创建队列
    @Bean
    public Queue pointQueue(){
        return new Queue("pointQueue");//取名自定义
    }

    @Bean
    public Queue topicQueue(){
        return new Queue("topic.pointQueue.a");
    }


//=================================================================================================

    @Bean//死信队列
    public Queue dealQueue(){
        return new Queue("dealQueue");
    }
    @Bean//死信交换机
    public DirectExchange dealDirectExchange(){
        return new DirectExchange("dealDirectExchange");
    }
    @Bean//死信队列绑定交换机
    public Binding dealQueueToDealDirectExchange(Queue dealQueue,DirectExchange dealDirectExchange){
        return BindingBuilder.bind(dealQueue).to(dealDirectExchange).with("dealKey");
    }


//=================================================================================================

    //创建要绑定死信的队列
    @Bean
    public Queue dealToQueue(){
        //关联死信交换机
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", "dealDirectExchange");
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key","dealKey");
        // x-message-ttl  声明队列的TTL
        args.put("x-message-ttl", 6000);//超过6秒后，消息未确认，放到指定的死信队列中！！

        //给当前队列取名
        return QueueBuilder.durable("dealToQueue").withArguments(args).build();
    }
    @Bean//创建要绑定死信的交换机
    public DirectExchange dealToDirectExchange(){
        return new DirectExchange("dealToDirectExchange");
    }
    @Bean//绑定已绑定死信的队列与交换机
    public Binding dealToQueueToDealToDirectExchange(Queue dealToQueue,DirectExchange dealToDirectExchange){
        return BindingBuilder.bind(dealToQueue).to(dealToDirectExchange).with("dealToKey");
    }





//=================================================================================================


    //4、创建交换机
    @Bean//直连交换机
    public DirectExchange pointDirectExchange(){
        return new DirectExchange("pointDirectExchange");
    }

    @Bean//通配交换机
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchangeA");
    }

    /*@Bean//订阅交换机
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }*/

   /* @Bean//Headers交换机（基本不用，效率太低）
    public HeadersExchange headersExchange(){
        return new HeadersExchange("");
    }*/







//=================================================================================================


    //5、绑定交换机
    @Bean//自动绑定，不用MQ页面手动绑定了,但要注意避免二次绑定冲突！
    public Binding pointQueueToPointDirectExchange(Queue pointQueue, DirectExchange pointDirectExchange){
        return BindingBuilder.bind(pointQueue).to(pointDirectExchange).with("pointKey");//with可以确定队列的名字，否则发送所有的队列
    }

    @Bean//还有！如果绑定了之后MQ收不到消息，在页面手动删除重新绑定！！
    public Binding topicExchangeAToTopicQueueA(Queue topicQueue,TopicExchange topicExchangeA){
        return BindingBuilder.bind(topicQueue).to(topicExchangeA).with("topic.#");//模糊匹配，一到多个
    }
    /*@Bean
    public Binding fanoutExchangeToPointQueue(Queue pointQueue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(pointQueue).to(fanoutExchange);//没有with，不需要key值
    }
    @Bean
    public Binding fanoutExchangeToTopicQueueA(Queue topicQueue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(topicQueue).to(fanoutExchange);//没有with，不需要key值
    }*/






//=================================================================================================


    //websocket 放入到spring容器
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}
