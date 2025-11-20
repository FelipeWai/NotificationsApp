package com.felipewai.mail.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipewai.mail.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventListener {

//    private final EmailService emailService;
    private final ObjectMapper mapper = new ObjectMapper();


    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void listenUserCreated(String message) throws Exception {
        System.out.println("User created: " + message);
    }

    @RabbitListener(queues = RabbitMQConfig.USER_UPDATED_QUEUE)
    public void listenUserUpdated(String message) throws Exception {
        System.out.println("User updated: " + message);
    }

}
