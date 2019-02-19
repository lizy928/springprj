package com.lizy.springprj.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created By Lizhengyuan on 19-2-14
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Object sessionUser = request.getSession().getAttribute("user:lizy");
        if(!Objects.isNull(sessionUser)){
            return true;
        }
        if(Objects.isNull(sessionUser)){
            String  msg = "请重新登录";
            try {
                response.getWriter().print(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
