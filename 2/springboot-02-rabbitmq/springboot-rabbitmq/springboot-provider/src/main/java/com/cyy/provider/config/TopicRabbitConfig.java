package com.cyy.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    //绑定键
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    @Bean
    public Queue firstQueue(){
        return new Queue(TopicRabbitConfig.man);
    }

    @Bean
    public Queue secondQueue(){
        return new Queue(TopicRabbitConfig.woman);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    /*
        将 firstQueue 和 topicExchange 绑定，并且绑定的键值为topic.man
        这样只要是消息携带的路由键是 topic.man，才会分发到该队列
     */
    @Bean
    public Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    @Bean
    public Binding bindingExchangeMessage2(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }

}
