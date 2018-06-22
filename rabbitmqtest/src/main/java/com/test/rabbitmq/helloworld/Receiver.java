package com.test.rabbitmq.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;

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

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
            System.in.read();
        } catch (IOException e)
        {

        }
        finally {
            channel.close();
            connection.close();
        }
    }
}
