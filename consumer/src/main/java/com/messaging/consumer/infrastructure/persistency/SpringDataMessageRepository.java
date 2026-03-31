package com.messaging.consumer.infrastructure.persistency;

import com.messaging.consumer.domain.entities.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataMessageRepository extends MongoRepository<MessageDocument, String> {
    List<MessageDocument> findByDestination(String destination);
    long countByDestinationAndCreatedDateAfter(
            String destination,
            LocalDateTime createdDate
    );
}
