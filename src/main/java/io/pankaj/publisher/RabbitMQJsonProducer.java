package io.pankaj.publisher;

import io.pankaj.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.json.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.json.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        log.info(String.format("JSON Message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey,user);
    }
}
