package com.test.rabbitmq.workqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Receiver
{
    public static final String QUEUE_NAME = "hell0";
    public static void main(String[] args) throws IOException
    {
        Connection connection = null;
        Channel channel = null;
        ConnectionFactory factory = new ConnectionFactory();
        try
        {
            connection = factory.newConnection("localhost");
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME);

            //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            final Channel finalChannel = channel;
            Consumer consumer = new DefaultConsumer(finalChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                    try {
                        doWork(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finalChannel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
            //是否开启自动应答,很多时候似乎需要关闭自动应答而采取手动应答。
            boolean autoAck = false;
            channel.basicConsume(QUEUE_NAME, autoAck, consumer);
            System.in.read();
        } catch (IOException e)
        {

        }
        finally {
            channel.close();
            connection.close();
        }
    }

    private static void doWork(String task) throws InterruptedException {
        String[] taskArr = task.split("_");
        TimeUnit.SECONDS.sleep(Long.valueOf(taskArr[1]));
    }

}
