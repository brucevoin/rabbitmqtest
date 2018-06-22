package com.test.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Calendar;

public class Sender {

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

            for (int i = 0; i< 8; i++) {
                String message = "task_" + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
            //System.in.read();
        } catch (IOException e)
        {

        }
        finally {
            channel.close();
            connection.close();
        }
    }
}
