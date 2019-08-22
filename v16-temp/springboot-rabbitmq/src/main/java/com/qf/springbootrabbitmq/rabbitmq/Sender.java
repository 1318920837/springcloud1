package com.qf.springbootrabbitmq.rabbitmq;/*
@author:g
@Date:2019/8/13
    */

import com.qf.springbootrabbitmq.Common.CommonConstant;
import com.qf.springbootrabbitmq.entity.ProductDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        rabbitTemplate.convertAndSend(CommonConstant.QUEUE_NAME,message);
    }
    public void send(ProductDto productDto){
        rabbitTemplate.convertAndSend(CommonConstant.QUEUE_NAME,productDto);
    }
}
