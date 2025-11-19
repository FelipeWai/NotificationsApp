package com.felipewai.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_EXCHANGE = "user.exchange";
    public static final String USER_CREATED_QUEUE = "user.created.queue";
    public static final String USER_UPDATED_QUEUE = "user.updated.queue";

    @Bean
    public TopicExchange userExchange (){
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue userCreatedQueue(){
        return new Queue(USER_CREATED_QUEUE, true);
    }

    @Bean
    public Queue userUpdatedQueue(){
        return new Queue(USER_UPDATED_QUEUE, true);
    }

    @Bean
    public Binding userCreatedBinding(){
        return BindingBuilder.bind(userCreatedQueue())
                .to(userExchange()).with("user.created");
    }

    @Bean
    public Binding userUpdatedBinding(){
        return BindingBuilder.bind(userUpdatedQueue())
                .to(userExchange()).with("user.updated");
    }
}
