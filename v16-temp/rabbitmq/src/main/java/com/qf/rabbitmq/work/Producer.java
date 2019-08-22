package com.qf.rabbitmq.work;/*
@author:g
@Date:2019/8/13
    */


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Producer {

    private static final String QUEUE_NAME="work-rabbitmq";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("120.27.243.183");
        factory.setVirtualHost("/1904");
        factory.setPort(5672);
        factory.setUsername("java1904");
        factory.setPassword("123");

        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i = 0; i < 10; i++) {
            String message="真的很菜";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        }
            System.out.println("发送成功");

    }
}
