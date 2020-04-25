package com.cyy.models.routing;

import com.cyy.models.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明 exchange ，指定类型为 direct
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //消息内容
        String message = "删除商品";
        //发送消息，并且指定 routing key 为：insert，代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        channel.close();
        connection.close();

    }

}
