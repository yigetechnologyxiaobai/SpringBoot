package com.cyy.models.topic;

import com.cyy.models.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明exchange，指定类型为 topic
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //消息内容
        String message = "新增商品";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "item.insert", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        channel.close();
        connection.close();

    }

}
