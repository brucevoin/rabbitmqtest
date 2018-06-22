package com.test.rabbitmq.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Sender {

    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String[] LOG_LEVEL_ARR = {"debug", "info", "error"};
    public static void main(String[] args) throws IOException, TimeoutException {
// 创建连接
        ConnectionFactory factory = new ConnectionFactory();
// 设置MabbitMQ, 主机ip或者主机名
        //factory.setHost("localhost");
// 创建一个连接
        Connection connection = factory.newConnection("localhost");
// 创建一个通道
        Channel channel = connection.createChannel();
// 指定一个转发器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
// 发送消息
        for (int i = 0; i < 10; i++) {
            int rand = new Random().nextInt(3);
            String routeKey = LOG_LEVEL_ARR[rand];
            String message = "Liang-MSG log : [" +routeKey+ "]" + UUID.randomUUID().toString();
// 发布消息至转发器
            channel.basicPublish(EXCHANGE_NAME, routeKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }

}
