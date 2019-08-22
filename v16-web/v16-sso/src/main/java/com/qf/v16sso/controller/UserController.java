package com.qf.v16sso.controller;/*
@author:g
@Date:2019/8/15
    */

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.service.IUserServcie;
import com.qf.v16.entity.TUser;
import com.qf.v16.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("user")
public class UserController {
    @Reference
    private IUserServcie userServcie;

    @RequestMapping("login")
    public String login(){
        return "login";
    }


    @RequestMapping("checklogin")
    @ResponseBody
    public ResultBean<String> checklogin(String username, String password, HttpServletResponse response) {
        System.out.println(username);
        System.out.println(password);
        TUser user1 = new TUser(username, password);
        ResultBean<String> resultBean = userServcie.checkLogin(user1);
        System.out.println(resultBean.getData());
        if("200".equals(resultBean.getStatusCode())){
            String key="user_token";
            Cookie cookie=new Cookie(key,resultBean.getData());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setDomain("qf.com");
            response.addCookie(cookie);
        }
        return resultBean;
    }
    @RequestMapping("checklogin4PC")

    public String  checklogin4PC(String username, String password, HttpServletResponse response) {
        System.out.println(username);
        System.out.println(password);
        TUser user1 = new TUser(username, password);
        ResultBean<String> resultBean = userServcie.checkLogin(user1);
        System.out.println(resultBean.getData());
        if("200".equals(resultBean.getStatusCode())){
            String key="user_token";
            Cookie cookie=new Cookie(key,resultBean.getData());
            cookie.setPath("/");
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
            return "redirect:http//localhost:9091/index/home";
        }
        return "login";
    }

   /* @RequestMapping("checkIsLogin")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name = "user_token",required = false)String uuid){
        if(uuid!=null){
            ResultBean resultBean = userServcie.checkIsLogin(uuid);
            if("200".equals(resultBean.getStatusCode())) {

                return resultBean;
            }
        }
        return new ResultBean("404","未登录");
    }*/
    @RequestMapping("checkIsLogin")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name = "user_token",required = false)String uuid){
        if(uuid!=null){
            ResultBean resultBean = userServcie.checkIsLogin(uuid);
            if("200".equals(resultBean.getStatusCode())) {

                return resultBean;
            }
        }
        return new ResultBean("404","未登录");
    }
    @RequestMapping("checkIsLogin4Jsonp")
    @ResponseBody
    public String checkIsLogin4Jsonp(@CookieValue(name = "user_token",required = false)String uuid, String callback) throws JsonProcessingException {

        ResultBean resultBean =null;
        if(uuid!=null){
            resultBean=userServcie.checkIsLogin(uuid);
        }else {
             resultBean=new ResultBean("404","未登录");
        }
        ObjectMapper objectMapper=new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultBean);
        return callback+"("+json+")";
    }

    @RequestMapping("loginout")
    @ResponseBody
    public ResultBean loginout(@CookieValue(name = "user_token",required = false)String uuid,HttpServletResponse response) {
        if(uuid!=null){
            ResultBean re = userServcie.loginout(uuid);
            Cookie cookie=new Cookie("user_token",uuid);
           cookie.setHttpOnly(true);
           cookie.setPath("/");
           cookie.setMaxAge(0);
           response.addCookie(cookie);
            return re;
        }
        return new ResultBean("404","操作失败");
    }

}
