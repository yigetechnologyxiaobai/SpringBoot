package com.cyy.models.work;

import com.cyy.models.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //循环发布任务
        for (int i = 0; i < 50; i++) {
            //消息内容
            String message = "task ... " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Send '" + message + "'");

            Thread.sleep(i * 2);
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }

}
