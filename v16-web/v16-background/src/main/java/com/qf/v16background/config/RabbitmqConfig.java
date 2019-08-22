package com.qf.v16background.config;/*
@author:g
@Date:2019/8/13
    */

import com.qf.v16.common.constant.RabbitMQContent;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConfig {
    @Bean
    public TopicExchange initTopicExchange() {
        return new TopicExchange(RabbitMQContent.BACKGROUND_PRODUCT_EXCHANGE, true, false);
    }
}
