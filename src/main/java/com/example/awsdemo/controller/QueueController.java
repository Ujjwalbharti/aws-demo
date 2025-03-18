package com.example.awsdemo.controller;

import com.example.awsdemo.models.QueueDTO;
import com.example.awsdemo.models.request.CreateQueueRequest;
import com.example.awsdemo.service.QueueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/sqs")
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;

    @PostMapping("create")
    public ResponseEntity<String> createQueue(@Valid @RequestBody CreateQueueRequest createQueueRequest) throws Exception {
        queueService.createQueue(createQueueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Queue created");
    }

    @GetMapping("fetch/{queueName}")
    public ResponseEntity<QueueDTO> fetchQueue(@PathVariable String queueName) throws Exception {
        QueueDTO queueDTO = queueService.findQueueBy(queueName);
        return ResponseEntity.ok(queueDTO);
    }

    @DeleteMapping("delete/{queueName}")
    public ResponseEntity<String> deleteQueue(@PathVariable String queueName) throws Exception {
        queueService.deleteQueue(queueName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Queue deleted");
    }
}
