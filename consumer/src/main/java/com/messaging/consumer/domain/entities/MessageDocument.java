package com.messaging.consumer.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
public class MessageDocument {
    @Id
    private String id;

    private String origin;
    private String destination;
    private String messageType;
    private String content;
    private Long processingTime;
    private LocalDateTime createdDate;
    private String error;

    public MessageDocument() {}

    public MessageDocument(String origin, String destination, String messageType, String content, Long processingTime, LocalDateTime createdDate, String error) {
        this.origin = origin;
        this.destination = destination;
        this.messageType = messageType;
        this.content = content;
        this.processingTime = processingTime;
        this.createdDate = createdDate;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
