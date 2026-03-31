package com.messaging.producer.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class MessageRequest {
    @Schema(description = "Sender phone number", example = "3001234567")
    @NotBlank(message = "Origin is required")
    private String origin;

    @Schema(description = "Receiver phone number", example = "3019876543")
    @NotBlank(message = "Destination is required")
    private String destination;

    @Schema(description = "Type of message", example = "TEXT")
    @NotBlank(message = "Message type is required")
    private String messageType;

    @Schema(description = "Message content or media URL", example = "Hello World")
    @NotBlank(message = "content is required")
    private String content;

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
}
