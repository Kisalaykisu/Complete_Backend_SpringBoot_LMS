package com.example.complete_backend_springboot_lms.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String QUEUE1 = "queue1_message";
    public static final String QUEUE2 = "queue2_message";
    public static final String EXCHANGE = "exchange_message";
    public static final String ROUTINGKEY1 = "routingkey1_message";
    public static final String ROUTINGKEY2 = "routingkey2_message";

    @Bean
    public Queue queue1(){
        return new Queue(QUEUE1);
    }

    @Bean
    public Queue queue2(){
        return new Queue(QUEUE2);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding1(Queue queue1,TopicExchange exchange){
        return BindingBuilder
                .bind(queue1)
                .to(exchange)
                .with(ROUTINGKEY1);
    }

    @Bean
    public Binding binding2(Queue queue2,TopicExchange exchange){
        return BindingBuilder
                .bind(queue2)
                .to(exchange)
                .with(ROUTINGKEY2);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
