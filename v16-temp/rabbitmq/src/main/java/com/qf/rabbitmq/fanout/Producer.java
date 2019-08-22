package com.qf.rabbitmq.fanout;/*
@author:g
@Date:2019/8/13
    */


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Producer {

    private static final String EXCHANGE_NAME="direct-exchange1";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("120.27.243.183");
        factory.setVirtualHost("/1904");
        factory.setPort(5672);
        factory.setUsername("java1904");
        factory.setPassword("123");

        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");


            String message="你的韩信真的很菜";
            channel.basicPublish(EXCHANGE_NAME,"cjxl",  null, message.getBytes());
            String message1="你的韩信真的很屌";
            channel.basicPublish(EXCHANGE_NAME,"dlxl",  null, message1.getBytes());


            System.out.println("发送成功");

    }
}
