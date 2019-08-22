package com.qf.v16search.Config;/*
@author:g
@Date:2019/8/13
    */

import com.qf.v16.common.constant.RabbitMQContent;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue initQueue() {
        return new Queue(RabbitMQContent.ADDOEUPDATE_SEARCH_QUEUE, false, false, false);
    }

    @Bean
    public TopicExchange initTopicExchange() {
        return new TopicExchange(RabbitMQContent.BACKGROUND_PRODUCT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingExchange(Queue initQueue, TopicExchange initTopicExchange) {
        return BindingBuilder.bind(initQueue).to(initTopicExchange).with("product.add");
    }
}
