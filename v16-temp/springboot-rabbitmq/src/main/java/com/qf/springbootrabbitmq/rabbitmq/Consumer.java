package com.qf.springbootrabbitmq.rabbitmq;/*
@author:g
@Date:2019/8/13
    */

import com.qf.springbootrabbitmq.Common.CommonConstant;
import com.qf.springbootrabbitmq.entity.ProductDto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = CommonConstant.QUEUE_NAME)
public class Consumer {
    @RabbitHandler
    public void process(String message){
        System.out.println("接受到的消息为"+message);
    }

    @RabbitHandler
    public void process(ProductDto productDto){
        System.out.println(productDto.getId()+"->"+productDto.getName());
    }
}
