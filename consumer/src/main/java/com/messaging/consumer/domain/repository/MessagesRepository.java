package com.messaging.consumer.domain.repository;

import com.messaging.consumer.domain.entities.MessageDocument;

import java.time.LocalDateTime;
import java.util.List;

public interface MessagesRepository {
    MessageDocument save(MessageDocument message);
    List<MessageDocument> getMessagesByDestination(String destination);
    long countMessagesLast24Hours(String destination, LocalDateTime since);
}
