package com.qf.springbootorder.controller;/*
@author:g
@Date:2019/8/19
    */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("order1")
public class PayController {
    @RequestMapping("topay")
    public void topay(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse,
                      String orderNo) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016100200645425",
                "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDOGS9p5nunfKlzQD0eT0e76+Oq8tAf1dVEZhtomnjrCXOQ27Q/EgZNm+XqYHDWpyyveCQk1/Yggq1RKMSWeI350jW9wfqApqw9u/AkQZcDkOQeDr6DF/Y4KT3m3U1Ec+PONJvl3DgVYWxOyxBu2pJMg5EE305D8PUz6pFYN+rdlFRt49H9qccgD8up0qC9K6XPkKTKjfXi/ujUTNdr2NCzN8ZjoX3mienZzj/1Ru45qpZH2EmizsYHBjtlt1wLZtWBIzhPIhdcQKnRfAyUEuesLZKI4d5xccFl3J+YXOnAEffUmzU/jXce6DnNPDFqGfucmpl1NjZC4q6V0TaRKBOBAgMBAAECggEBAIBc60z4AngUiq1/ixF/q1v91XS5eqr5UbUiK1wBKeKpplLIpADMRyS7Pn21x1HKGWjIWSqhX2hsO2pbC+Xz++lm5ZXkZRMPvCNPFIhgWaAc9BdMc5fOqsnb1fQeTWufCnl6BscpW7zlSsHgc8G3XHtPTllMg8CJKQ9Gv3PEkAGkrKpMsUrgCFNeJkrNbgjEfeazKhSyZ9Chm4aBl+gI/MChxfv8d2kmHb/Xj5W9lX0ELNFrI2Jnin9wYxI77HYPZkSRof7dc0qKEkv2Ts8HIvNqwHBiW9SzF8kU7FGSwFsOaO6exBe3e6M4t2DXY+i6CFJU15caudc5uNsyKEXgL4ECgYEA6klGI66AzG0eavzzxJ1QpCP9AMTCLMv/v4TZVusC4vh+XViZTP6ID4Hg2kiOjsYSlS3TivHCFcSizVQtapoSIE5hBr62b6VJNJSXuwdS3RmgUEdcO0TnsoJwy02Q2UAxafKob/FZP0OBVNEe6tbyDTC3rSplzM36bL3p8xwEeYkCgYEA4TMex/AylH+Zcy75K7yYeMAnZeh9zKiBOUpAS/+h1KHcGgu03lKHjFxidOG789y/d5cG9cXdrrKibsRddaaxFqlOw9Fsoe6msqAenafBsnnhcGV0QDMBhO8bd4etLs5IHQKGkO7zj4Gp4q355foJu4rzB4MOnyfXwxK52MzT5DkCgYEAlGg5/9xIRjZQ8GCvYbptHKyXVWdNwOawaHcU3ZQczf2uvh6jbTxGn9wbvOn9M7+QgeL3f6pI0VUv83UBF+fk14YOvCEOm4ZN6vq9aoHnIZtcC75IVFr/kfpCzOWjfZrGMmC+BbsSV5rBUCLvvL7PsT9M+mpC9gr8OOnMSm1TAFECgYEA0OwcImE0i4/VTIbHo0vUKDIcWu7VxoiNWhkIjXkbKakACSnTIm5uUkljDgxd0l95Qfla+6gVgX04NIr4Ka37nIp2CDTCE3jULeNeg+SeTnXEMAHvV5/6SNmLIdk9Ua8ON9uf+9WzH++rLCE7NaBCZzsBPHbZmc3c3Z0BzaA49AECgYEAgKPDmH/R1Ww+2HjGGtA44BJK3qlSGMqZ92MN+sqlB1y/C3gEu4tw1TXxze7SwUyXUlU6UlXEIpWc8t2mUOpLgMrzorEFbWEDXB0jt1DDN+KV4uPQsbpKf0R32+uJQMUQ6d8XDck3108P5uokDa6967ynddQV+0CTPAcB+uc3kww=",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzhkvaeZ7p3ypc0A9Hk9Hu+vjqvLQH9XVRGYbaJp46wlzkNu0PxIGTZvl6mBw1qcsr3gkJNf2IIKtUSjElniN+dI1vcH6gKasPbvwJEGXA5DkHg6+gxf2OCk95t1NRHPjzjSb5dw4FWFsTssQbtqSTIORBN9OQ/D1M+qRWDfq3ZRUbePR/anHIA/LqdKgvSulz5Ckyo314v7o1EzXa9jQszfGY6F95onp2c4/9UbuOaqWR9hJos7GBwY7ZbdcC2bVgSM4TyIXXECp0XwMlBLnrC2SiOHecXHBZdyfmFzpwBH31Js1P413Hug5zTwxahn7nJqZdTY2QuKuldE2kSgTgQIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://2km82k.natappfree.cc/order/returnURL");
        alipayRequest.setNotifyUrl("http://2km82k.natappfree.cc/order/notifyUrl");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"orderNo\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":8888," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"" +

                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
    @RequestMapping("returenURL")
    public String returenURL(){
        return "return_url";
    }
    @RequestMapping("notifyUrl")
    public void notifyUrl(){
        System.out.println(".................");
    }

}
