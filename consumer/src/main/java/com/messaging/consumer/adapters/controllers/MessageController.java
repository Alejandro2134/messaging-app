package com.messaging.consumer.adapters.controllers;

import com.messaging.consumer.application.dto.MessagesResponse;
import com.messaging.consumer.application.use_cases.GetMessagesByDestination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@Tag(name = "Messages")
public class MessageController {
    private final GetMessagesByDestination getMessagesByDestinationUseCase;

    public MessageController(GetMessagesByDestination getMessagesByDestinationUseCase) {
        this.getMessagesByDestinationUseCase = getMessagesByDestinationUseCase;
    }

    @Operation(summary = "Get messages by destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Messages list")
    })
    @GetMapping
    public List<MessagesResponse> getMessagesByDestination(@RequestParam String destination) {
        return getMessagesByDestinationUseCase.execute(destination);
    }

}
