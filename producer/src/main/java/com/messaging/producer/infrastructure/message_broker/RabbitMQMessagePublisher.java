package com.messaging.producer.infrastructure.message_broker;

import com.messaging.producer.application.dto.MessageRequest;
import com.messaging.producer.config.RabbitMQConfig;
import com.messaging.producer.domain.repository.MessagePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessagePublisher implements MessagePublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQMessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(MessageRequest message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message, msg -> {
            msg.getMessageProperties().setHeader(
                    "receivedTimestamp",
                    System.currentTimeMillis()
            );
            return msg;
        });
    }
}
