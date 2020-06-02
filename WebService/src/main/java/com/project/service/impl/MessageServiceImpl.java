package com.project.service.impl;
import com.project.service.IMessage;

import javax.jws.WebService;

@WebService
public class MessageServiceImpl implements IMessage {

    @Override
    public String send(String tag) {

        String message="";
        if(tag.equals("1")){
            message="成都天气预报";
        }else if (tag.equals("2")){
            message="广州天气预报";
        }
        return message;
    }
}
