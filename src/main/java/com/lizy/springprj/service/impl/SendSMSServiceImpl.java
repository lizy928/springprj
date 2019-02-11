package com.lizy.springprj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lizy.springprj.service.SendSMSService;
import com.lizy.springprj.thread.SendSMSCallable;
import com.lizy.springprj.thread.ThreadPool;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created By Lizhengyuan on 19-2-11
 */
@Service
public class SendSMSServiceImpl implements SendSMSService {

    @Override
    public JSONObject sendSMS(String mobile, String content, SendSMSService sendSMSService) {
        SendSMSCallable sendSMSCallable = new SendSMSCallable(sendSMSService);
        ExecutorService smsExecutorService = ThreadPool.getInstance().getSmsExecutorService();
        Future<JSONObject> future = smsExecutorService.submit(sendSMSCallable);
        JSONObject jsonObject = null;
        try {
            jsonObject = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("sendn sms ...");
        return jsonObject;
    }
}
