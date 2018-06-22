package com.test.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main
{
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        RPCClient client = new RPCClient();
        String response = client.call("30");
        System.out.println(response);
    }
}
