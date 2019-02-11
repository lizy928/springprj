package com.lizy.springprj.thread;

import com.alibaba.fastjson.JSONObject;
import com.lizy.springprj.service.SendSMSService;

import java.util.concurrent.Callable;

/**
 * Created By Lizhengyuan on 19-2-11
 */
public class SendSMSCallable implements Callable {

    private SendSMSService sendSMSService;

    public SendSMSCallable(SendSMSService sendSMSService){
        this.sendSMSService = sendSMSService;
    }

    @Override
    public JSONObject call() {
        try {
            sendSMSService.sendSMS("123213","发送短信", sendSMSService);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","0");
            jsonObject.put("msg","success");
            return jsonObject;
        }catch (Exception e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","-1");
            jsonObject.put("msg","error");
            return jsonObject;
        }
    }
}
