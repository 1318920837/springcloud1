package com.qf.springbootrabbitmq.rabbitmq;/*
@author:g
@Date:2019/8/13
    */

import com.qf.springbootrabbitmq.Common.CommonConstant;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue initQueue(){
        return new Queue(CommonConstant.QUEUE_NAME,false,false,false);
    }
}
