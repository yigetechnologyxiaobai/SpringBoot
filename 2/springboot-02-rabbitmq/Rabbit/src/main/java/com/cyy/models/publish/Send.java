package com.cyy.models.publish;

import com.cyy.models.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明exchange，指定类型为 fanout
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //消息内容
        String message = "Hello everyone";
        //发布消息到exchange
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");

        channel.close();
        connection.close();

    }


}
