package com.project;
import com.project.client.MessageServiceImpl;
import com.project.client.MessageServiceImplService;

/**
 * 客户端
 */
public class Mains {

    public static void main(String[] args) {

        //首先先下载代码，项目右键WebService然后Generate...选择发布url和JAX_WS
        //这里的代码是从发布网址下载的
        MessageServiceImplService messageServiceImplService = new MessageServiceImplService();
        MessageServiceImpl messageService = messageServiceImplService.getMessageServiceImplPort();

        //得到方法返回数据
        String str = messageService.send("2");
        System.out.println(str);

    }
}
