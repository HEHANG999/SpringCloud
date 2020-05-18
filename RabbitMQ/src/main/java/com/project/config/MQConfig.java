package com.project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    //3、创建队列
    @Bean
    public Queue pointQueue(){
        return new Queue("pointQueue");//取名自定义
    }

    @Bean
    public Queue topicQueue(){
        return new Queue("topic.Queue.a");
    }

    //4、创建交换机
    @Bean//点对点式
    public DirectExchange pointDirectExchange(){
        return new DirectExchange("pointDirectExchange");
    }

    @Bean//模糊匹配式
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean//直接匹配式
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    //5、绑定交换机
    @Bean//自动绑定，不用MQ页面手动绑定了
    public Binding pointQueueToPointDirectExchange(Queue pointQueue,DirectExchange pointDirectExchange){
        return BindingBuilder.bind(pointQueue).to(pointDirectExchange).with("pointKey");//with可以确定队列的名字，否则发送所有的队列
    }
    @Bean
    public Binding topicExchangeAToTopicQueueA(Queue topicQueue,TopicExchange topicExchangeA){
        return BindingBuilder.bind(topicQueue).to(topicExchangeA).with("topic.#");//模糊匹配，一到多个
    }
    @Bean
    public Binding fanoutExchangeToPointQueue(Queue pointQueue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(pointQueue).to(fanoutExchange);//没有with，不需要key值
    }
    @Bean
    public Binding fanoutExchangeToTopicQueueA(Queue topicQueue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(topicQueue).to(fanoutExchange);//没有with，不需要key值
    }

}
