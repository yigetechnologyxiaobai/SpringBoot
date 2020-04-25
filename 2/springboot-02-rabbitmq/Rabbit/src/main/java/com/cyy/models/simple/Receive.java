package com.cyy.models.simple;

import com.cyy.models.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body即消息体
                String msg = new String(body);
                System.out.println(" [x] received : " + msg + "!");
                //手动ACK
                //channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        //监听队列，第二个参数：是否自动进行消息确认
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }

}
