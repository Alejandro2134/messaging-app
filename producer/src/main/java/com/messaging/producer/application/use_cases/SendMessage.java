package com.messaging.producer.application.use_cases;

import com.messaging.producer.application.dto.MessageRequest;
import com.messaging.producer.application.exception.OriginNotFound;
import com.messaging.producer.domain.repository.MessagePublisher;
import com.messaging.producer.domain.repository.OriginsRepository;
import org.springframework.stereotype.Service;

@Service
public class SendMessage {
    private final OriginsRepository originsRepository;
    private final MessagePublisher messagePublisher;

    public SendMessage(OriginsRepository originsRepository, MessagePublisher messagePublisher) {
        this.originsRepository = originsRepository;
        this.messagePublisher = messagePublisher;
    }

    public void execute(MessageRequest message) {
        var origin = originsRepository.findByPhoneNumber(message.getOrigin());

        if(origin.isEmpty()) {
            throw new OriginNotFound("Origin not found");
        }

        messagePublisher.publish(message);
    }
}
