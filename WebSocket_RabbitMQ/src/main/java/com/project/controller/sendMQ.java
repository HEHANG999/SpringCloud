package com.project.controller;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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


/*=================================MQ回调==================================================================*/


    //消息确认机制（消息放到交换机（已绑定队列）中后，就会执行此方法）
    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback(){
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
           /* System.out.println("CorrelationData="+correlationData);
            System.out.println("b="+b);
            System.out.println("s="+s);*/
            //写业务 如果b=true代表数据已放入队列，根据correlationData（数据ID）修改本地它的数据tag=1，代表真实数据

            System.out.println("已放入队列中");
        }
    };

    //回退机制
    RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {
           /* System.out.println("Message="+message);
            System.out.println("i="+i);
            System.out.println("s="+s);
            System.out.println("s1="+s1);
            System.out.println("s2="+s2);*/

            System.out.println("放入队列失败");
        }
    };




/*=================================rabbitMQ==================================================================*/

    @RequestMapping("sendStr")
    @ResponseBody
    public String sendStr(String str){
        rabbitTemplate.setMandatory(true);//开启消息到达交换机确认机制（消息确认机制）
        rabbitTemplate.setConfirmCallback(confirmCallback); //绑定回调函数--确认
        rabbitTemplate.setReturnCallback(returnCallback);//绑定回调函数--回退

        CorrelationData correlationData = new CorrelationData(UUID);//消息确认唯一标志，必须保证唯一性
        //System.out.println(correlationData.getId()+"/uuid="+UUID);

        //发送
        rabbitTemplate.convertAndSend("pointDirectExchange","pointkey",str);

        return "发送成功6666/"+str;
    }



    @RequestMapping("sendTopic")
    @ResponseBody
    public String sendTopic(String str){//不开启确认机制，监听就一个message参数就可以了

        //routingKey只要以topic开头，都可以匹配到topicExchangeA交换机
        rabbitTemplate.convertAndSend("topicExchangeA","topic.hello",str);

        return "发送成功";
    }






/*=================================webSocket==================================================================*/







}
