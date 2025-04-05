package com.example.awsdemo.controller;

import com.example.awsdemo.models.QueueDTO;
import com.example.awsdemo.models.request.CreateQueueRequest;
import com.example.awsdemo.models.response.ApiResponse;
import com.example.awsdemo.service.QueueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/sqs")
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse> createQueue(@Valid @RequestBody CreateQueueRequest createQueueRequest) throws Exception {
        queueService.createQueue(createQueueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Queue created successfully"));
    }

    @GetMapping("fetch/queues")
    public ResponseEntity<List<QueueDTO>> fetchAllQueue() throws Exception {
        List<QueueDTO> queues = queueService.findAllQueues();
        return ResponseEntity.ok(queues);
    }

    @GetMapping("fetch/queue/{queueName}")
    public ResponseEntity<QueueDTO> fetchQueue(@PathVariable String queueName) throws Exception {
        QueueDTO queueDTO = queueService.findQueueBy(queueName);
        return ResponseEntity.ok(queueDTO);
    }

    @DeleteMapping("delete/{queueName}")
    public ResponseEntity<ApiResponse> deleteQueue(@PathVariable String queueName) throws Exception {
        queueService.deleteQueue(queueName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("Queue deleted successfully"));
    }
}
