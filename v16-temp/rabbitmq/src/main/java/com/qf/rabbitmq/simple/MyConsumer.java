package com.qf.rabbitmq.simple;/*
@author:g
@Date:2019/8/13
    */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer {
    private static final String QUEUE_NAME="simple-rabbitmq";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.27.243.183");
        factory.setVirtualHost("/1904");
        factory.setPort(5672);
        factory.setUsername("java1904");
        factory.setPassword("123");

        Connection connection = factory.newConnection();

        Channel channel=connection.createChannel();
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message=new String(body,"utf-8");
                System.out.println(message);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
