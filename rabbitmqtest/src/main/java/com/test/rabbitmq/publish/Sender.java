package com.test.rabbitmq.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    private static final String EXCHANGE_NAME = "logs";
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
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
// 发送消息
        String message = "Liang-MSG log.";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
// 关闭频道和连接
        channel.close();
        connection.close();
    }

}
