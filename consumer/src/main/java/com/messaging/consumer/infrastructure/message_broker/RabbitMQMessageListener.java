package com.messaging.consumer.infrastructure.message_broker;

import com.messaging.consumer.application.use_cases.ProcessMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener {
    private final ProcessMessage processMessageUseCase;

    public RabbitMQMessageListener(ProcessMessage processMessageUseCase) {
        this.processMessageUseCase = processMessageUseCase;
    }

    @RabbitListener(queues = "message.queue")
    public void receiveMessage(Message message) {
        processMessageUseCase.execute(message);
    }
}
