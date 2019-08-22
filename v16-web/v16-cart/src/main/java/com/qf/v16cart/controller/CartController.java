package com.qf.v16cart.controller;/*
@author:g
@Date:2019/8/17
    */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.cart.cartapi.ICartService;
import com.qf.v16.pojo.ResultBean;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("cart")
public class CartController {

    @Reference
    private ICartService iCartService;
    @RequestMapping("add/{productId}/{count}")
    public ResultBean add(@PathVariable("productId") Long productId,
                          @PathVariable("count") Integer count,
                          @CookieValue(name = "user_cart",required = false) String uuid,
                          HttpServletResponse response){
        if(uuid == null || "".equals(uuid)){
            uuid= UUID.randomUUID().toString();
            Cookicadd(uuid, response);
        }
    return iCartService.add(uuid,productId,count);
    }

    private void Cookicadd(@CookieValue(name = "user_cart", required = false) String uuid, HttpServletResponse response) {
        Cookie cookie=new Cookie("user_cart",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        cookie.setDomain("qf.com");
        response.addCookie(cookie);
    }

    @RequestMapping("query")
    public ResultBean query(@CookieValue(name = "user_cart",required = false) String uuid,
                            HttpServletResponse response){
        if(uuid==null||"".equals(uuid)){
            return new ResultBean("404","qwe");

        }
        Cookicadd(uuid,response);
        return iCartService.query(uuid);
    }
    @RequestMapping("upadte/{productId}/{count}")
    public ResultBean upadte(@PathVariable("productId") Long productId,
                          @PathVariable("count") Integer count,
                          @CookieValue(name = "user_cart",required = false) String uuid,
                          HttpServletResponse response) {
        if (uuid == null || "".equals(uuid)) {
            return new ResultBean("404", "qwe");

        }
        Cookicadd(uuid,response);
        return iCartService.update(uuid,productId,count);
    }
    @RequestMapping("del/{productId}")
    public ResultBean del(@PathVariable("productId") Long productId,

                             @CookieValue(name = "user_cart",required = false) String uuid,
                             HttpServletResponse response) {
        if (uuid == null || "".equals(uuid)) {
            return new ResultBean("404", "qwe");

        }
        Cookicadd(uuid,response);
        return iCartService.del(uuid,productId);
    }
}
