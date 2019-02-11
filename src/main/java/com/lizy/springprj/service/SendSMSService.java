package com.lizy.springprj.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created By Lizhengyuan on 19-2-11
 */
public interface SendSMSService {

    JSONObject sendSMS(String mobile, String content, SendSMSService sendSMSService);

}
