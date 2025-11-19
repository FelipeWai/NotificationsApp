package com.felipewai.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.felipewai.user.config.RabbitMQConfig.USER_EXCHANGE;

@Service
@RequiredArgsConstructor
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public void publish(String routingKey, Object payload){
        try{
            String json = mapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend(USER_EXCHANGE, routingKey, json);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Error serializing event to Json", e);
        }
    }

}
