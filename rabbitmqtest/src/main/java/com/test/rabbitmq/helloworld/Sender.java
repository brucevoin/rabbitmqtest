package com.test.rabbitmq.helloworld;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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

            String message = "hello boy " + Calendar.getInstance().getTime().toString();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
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
