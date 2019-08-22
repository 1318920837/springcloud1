package com.qf.v16cart.interceptor;/*
@author:g
@Date:2019/8/17
    */

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor  implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user_token".equals(cookie.getName())) {
                    String jwtToken = cookie.getValue();
                }
            }
        }
        return true;
    }
}
