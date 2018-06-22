package com.test.rabbitmq.route;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DebugMsgReceiver
{
    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String[] LOG_LEVEL_ARR = {"debug", "info", "error"};
    public static void main(String[] args) throws IOException, TimeoutException {
// 创建连接
        ConnectionFactory factory = new ConnectionFactory();
// 设置MabbitMQ, 主机ip或者主机名
       // factory.setHost("localhost");
// 创建一个连接
        Connection connection = factory.newConnection("localhost");
// 创建一个通道
        Channel channel = connection.createChannel();
// 指定一个转发器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 设置日志级别
        //int rand = new Random().nextInt(3);

        int rand = 0;
        String errorRouteKey = LOG_LEVEL_ARR[rand];
// 创建一个非持久的、唯一的、自动删除的队列
        String queueName = channel.queueDeclare().getQueue();
// 绑定转发器和队列
        channel.queueBind(queueName, EXCHANGE_NAME, errorRouteKey);
// 打印
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
// 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
