package com.qf.rabbitmq.fanout;/*
@author:g
@Date:2019/8/13
    */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer1 {
    private static final String QUEUE_NAME="direct-exchange2";
    private static final String EXCHANGE_NAME="direct-exchange1";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.27.243.183");
        factory.setVirtualHost("/1904");
        factory.setPort(5672);
        factory.setUsername("java1904");
        factory.setPassword("123");

        Connection connection = factory.newConnection();

        Channel channel=connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"dlxl");
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message=new String(body,"utf-8");
                System.out.println("客户端一"+message);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
