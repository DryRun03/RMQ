package io.pankaj.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueue;
    @Value("${rabbitmq.json.exchange.name}")
    private String jsonExchange;
    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    //Spring Bean for rabbitMQ Queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //Spring Bean for rabbit MQ Exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //binding between queue & exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

            // JSON //
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }
    @Bean
    public TopicExchange jsonExchange(){
        return new TopicExchange(jsonExchange);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(jsonExchange())
                .with(jsonRoutingKey);
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
