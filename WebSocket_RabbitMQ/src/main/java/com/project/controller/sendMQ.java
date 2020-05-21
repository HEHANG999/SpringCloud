package com.project.controller;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * MQ发送类
 */
@Controller
public class sendMQ {



    //注入模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //UUID
    String UUID = java.util.UUID.randomUUID().toString();


/*=================================rabbitMQ==================================================================*/
    @RequestMapping("sendStr")
    @ResponseBody
    public String sendStr(String str){

        //发送
        rabbitTemplate.convertAndSend("pointDirectExchange","pointkey",str);

        return "发送成功6666/"+str;
    }



    @RequestMapping("sendTopic")
    @ResponseBody
    public String sendTopic(String str){

        //routingKey只要以topic开头，都可以匹配到topicExchangeA交换机
        rabbitTemplate.convertAndSend("topicExchangeA","topic.hello",str);

        return "发送成功";
    }



    @RequestMapping("web")
    public String webHtml(){
        return "Web.html";
    }




/*=================================webSocket==================================================================*/


    @RequestMapping("sendWeb")
    @ResponseBody
    public String sendWeb1(String str){
        System.out.println(str);
        return str;
    }




}
