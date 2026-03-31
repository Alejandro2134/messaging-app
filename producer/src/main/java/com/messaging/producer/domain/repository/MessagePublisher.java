package com.messaging.producer.domain.repository;

import com.messaging.producer.application.dto.MessageRequest;

public interface MessagePublisher {
    void publish(MessageRequest message);
}
