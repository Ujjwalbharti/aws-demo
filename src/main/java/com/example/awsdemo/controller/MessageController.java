package com.example.awsdemo.controller;

import com.example.awsdemo.models.MessageDTO;
import com.example.awsdemo.models.request.AcknowledgeMessageRequest;
import com.example.awsdemo.models.request.MessagePollRequest;
import com.example.awsdemo.models.request.MessageRequest;
import com.example.awsdemo.models.response.ApiResponse;
import com.example.awsdemo.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/sqs")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("send-messages")
    public ResponseEntity<ApiResponse> sendMessage(@Valid @RequestBody MessageRequest messageRequest) throws Exception {
        messageService.sendMessage(messageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Message sent successfully"));
    }

    @GetMapping("poll-messages")
    public ResponseEntity<List<MessageDTO>> pollMessage(@Valid @RequestBody MessagePollRequest request) throws Exception {
        List<MessageDTO> messages = messageService.pollMessages(request);
        return ResponseEntity.ok(messages);
    }

    @DeleteMapping("acknowledge-messages")
    public ResponseEntity<ApiResponse> acknowledgeMessage(@Valid @RequestBody AcknowledgeMessageRequest messageRequest) throws Exception {
        messageService.acknowledgeMessage(messageRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("acknowledge message acknowledged successfully"));
    }

}
