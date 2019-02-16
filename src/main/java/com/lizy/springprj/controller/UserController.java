package com.lizy.springprj.controller;

import com.lizy.springprj.entity.User;
import com.lizy.springprj.service.SendSMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Lizhengyuan on 19-1-30
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private SendSMSService sendSMSService;

    @RequestMapping("getUser")
    public Object getUserInfo(String userId){
        User user = new User(1,"lizy",2);
        return user;
    }

    @RequestMapping("sendSMS")
    public Object sendSMS(String userId){
        sendSMSService.sendSMS(null,null, sendSMSService);
        return "success";
    }
}
