package com.project;
import com.project.service.IMessage;
import com.project.service.impl.MessageServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * 发布代码
 */
public class Main {

    public static void main(String[] args) {

        IMessage messageService = new MessageServiceImpl();
        //发布（将messageService发布到该网址，便于其它人下载后编译且访问）
        Endpoint.publish("http://192.168.43.252:8888/message", messageService);

        //访问url：http://192.168.43.252:8888/message?wsdl
    }
}
