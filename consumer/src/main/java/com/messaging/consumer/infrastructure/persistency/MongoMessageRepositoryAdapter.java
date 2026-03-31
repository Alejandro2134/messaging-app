package com.messaging.consumer.infrastructure.persistency;

import com.messaging.consumer.domain.entities.MessageDocument;
import com.messaging.consumer.domain.repository.MessagesRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MongoMessageRepositoryAdapter implements MessagesRepository {
    private final SpringDataMessageRepository repository;

    public MongoMessageRepositoryAdapter(SpringDataMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public MessageDocument save(MessageDocument message) {
        return repository.save(message);
    }

    @Override
    public List<MessageDocument> getMessagesByDestination(String destination) {
        return this.repository.findByDestination(destination);
    }

    @Override
    public long countMessagesLast24Hours(String destination, LocalDateTime since) {
        return repository.countByDestinationAndCreatedDateAfter(destination, since);
    }
}
