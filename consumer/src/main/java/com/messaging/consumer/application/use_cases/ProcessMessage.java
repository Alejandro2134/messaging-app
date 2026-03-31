package com.messaging.consumer.application.use_cases;

import com.messaging.consumer.application.dto.IncomingMessage;
import com.messaging.consumer.domain.entities.MessageDocument;
import com.messaging.consumer.domain.repository.MessagesRepository;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class ProcessMessage {
    private final MessagesRepository messagesRepository;

    public ProcessMessage(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void execute(Message message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        IncomingMessage incomingMessage = new ObjectMapper().readValue(body, IncomingMessage.class);

        Object timestampHeader = message
                .getMessageProperties()
                .getHeaders()
                .get("receivedTimestamp");

        long receivedTimestamp = Long.parseLong(timestampHeader.toString());
        long processingTime = System.currentTimeMillis() - receivedTimestamp;
        long count = messagesRepository.countMessagesLast24Hours(
                incomingMessage.getDestination(),
                LocalDateTime.now().minusHours(24)
        );

        MessageDocument messageDocument;

        if(count >= 3) {
            messageDocument = new MessageDocument(
                    incomingMessage.getOrigin(),
                    incomingMessage.getDestination(),
                    incomingMessage.getMessageType(),
                    incomingMessage.getContent(),
                    processingTime,
                    LocalDateTime.now(),
                    "Destination reached maximum allowed messages"
            );
        } else {
            messageDocument = new MessageDocument(
                    incomingMessage.getOrigin(),
                    incomingMessage.getDestination(),
                    incomingMessage.getMessageType(),
                    incomingMessage.getContent(),
                    processingTime,
                    LocalDateTime.now(),
                    null
            );
        }

        messagesRepository.save(messageDocument);
    }
}
