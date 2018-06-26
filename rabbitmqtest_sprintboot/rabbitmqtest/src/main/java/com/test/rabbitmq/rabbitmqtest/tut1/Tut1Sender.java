package com.test.rabbitmq.rabbitmqtest.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    //@Scheduled(fixedDelay = 1000, initialDelay = 500)
    @Scheduled(cron="0/5 * *  * * ? ")
    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
