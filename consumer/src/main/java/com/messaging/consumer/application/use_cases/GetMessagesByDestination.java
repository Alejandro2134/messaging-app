package com.messaging.consumer.application.use_cases;

import com.messaging.consumer.application.dto.MessagesResponse;
import com.messaging.consumer.domain.entities.MessageDocument;
import com.messaging.consumer.domain.repository.MessagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMessagesByDestination {
    private final MessagesRepository messagesRepository;

    public GetMessagesByDestination(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public List<MessagesResponse> execute(String destination) {
        List<MessageDocument> messages = messagesRepository.getMessagesByDestination(destination);

        return messages.stream()
                .map(this::toDto)
                .toList();
    }

    private MessagesResponse toDto(MessageDocument message) {
        return new MessagesResponse(
                message.getOrigin(),
                message.getDestination(),
                message.getMessageType(),
                message.getContent(),
                message.getProcessingTime(),
                message.getCreatedDate(),
                message.getError()
        );
    }
}
