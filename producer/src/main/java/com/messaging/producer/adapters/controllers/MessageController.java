package com.messaging.producer.adapters.controllers;

import com.messaging.producer.application.dto.MessageRequest;
import com.messaging.producer.application.use_cases.SendMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@Tag(name = "Messages")
public class MessageController {
    private final SendMessage sendMessageUseCase;

    public MessageController(SendMessage sendMessageUseCase) {
        this.sendMessageUseCase = sendMessageUseCase;
    }

    @Operation(summary = "Send a message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Origin not found")
    })
    @SecurityRequirement(name = "basicAuth")
    @PostMapping
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageRequest request)
    {
        sendMessageUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
