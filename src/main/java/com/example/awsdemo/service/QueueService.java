package com.example.awsdemo.service;

import com.example.awsdemo.exception.CustomException;
import com.example.awsdemo.models.QueueDTO;
import com.example.awsdemo.models.request.CreateQueueRequest;

import java.util.List;

public interface QueueService {
    void createQueue(CreateQueueRequest createQueueRequest) throws CustomException;

    void deleteQueue(String queueName) throws CustomException;

    QueueDTO findQueueBy(String queueName, Long userId) throws CustomException;

    QueueDTO findQueueBy(String queueName) throws CustomException;

    List<QueueDTO> findAllQueues() throws CustomException;
}
