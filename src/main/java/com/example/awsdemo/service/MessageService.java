package com.example.awsdemo.service;

import com.example.awsdemo.exception.CustomException;
import com.example.awsdemo.models.MessageDTO;
import com.example.awsdemo.models.request.AcknowledgeMessageRequest;
import com.example.awsdemo.models.request.MessagePollRequest;
import com.example.awsdemo.models.request.MessageRequest;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageRequest messageRequest) throws CustomException;

    List<MessageDTO> pollMessages(MessagePollRequest request) throws CustomException;

    void acknowledgeMessage(AcknowledgeMessageRequest request) throws CustomException;
}
