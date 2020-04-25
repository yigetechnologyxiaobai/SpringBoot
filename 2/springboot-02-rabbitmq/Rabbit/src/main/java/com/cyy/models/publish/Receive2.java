package com.cyy.models.publish;

import com.cyy.models.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive2 {

    private final static String QUEUE_NAME = "fanout_exchange_queue_2";

    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列和交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
            }
        };
        //监听队列，自动返回完成
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
