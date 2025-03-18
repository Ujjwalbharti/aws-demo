package com.example.awsdemo.service;

import com.example.awsdemo.cache.RedisService;
import com.example.awsdemo.entity.Message;
import com.example.awsdemo.exception.CustomException;
import com.example.awsdemo.exception.QueueNotFoundException;
import com.example.awsdemo.models.MessageDTO;
import com.example.awsdemo.models.QueueDTO;
import com.example.awsdemo.models.UserDTO;
import com.example.awsdemo.models.request.AcknowledgeMessageRequest;
import com.example.awsdemo.models.request.MessagePollRequest;
import com.example.awsdemo.models.request.MessageRequest;
import com.example.awsdemo.repository.MessageRepository;
import com.example.awsdemo.security.SecurityHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final QueueService queueService;
    private final RedisService redisService;

    @Override
    @Transactional
    public void sendMessage(MessageRequest messageRequest) throws CustomException {
        if (CollectionUtils.isEmpty(messageRequest.getMessages())) {
            return;
        }
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        UserDTO userDTO = userService.getUserByUsername(userName);
        QueueDTO queueDTO = queueService.findQueueBy(messageRequest.getQueueName(), userDTO.getId());
        if (queueDTO == null) {
            throw new QueueNotFoundException();
        }
        var messages = getMessageEntities(messageRequest, queueDTO);
        messageRepository.saveAll(messages);
    }

    @Override
    public List<MessageDTO> pollMessages(MessagePollRequest request) throws CustomException {
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        UserDTO userDTO = userService.getUserByUsername(userName);
        QueueDTO queueDTO = queueService.findQueueBy(request.getQueueName(), userDTO.getId());
        if (queueDTO == null) {
            throw new QueueNotFoundException();
        }
        List<String> messageIds = redisService.getList(getKey(userName,request.getQueueName()));
        var messages = messageRepository.pollMessage(queueDTO.getId(), request.getLimit(), messageIds);
        if (CollectionUtils.isEmpty(messages)) {
            return new ArrayList<>();
        }
        messages.forEach(message -> messageIds.add(message.getMessageId()));
        redisService.setList(getKey(userName, request.getQueueName()), messageIds, queueDTO.getVisibilityTimeout());
        return messages.stream().map(this::getDto).toList();
    }

    @Override
    @Transactional
    public void acknowledgeMessage(AcknowledgeMessageRequest request) throws CustomException {
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        UserDTO userDTO = userService.getUserByUsername(userName);
        QueueDTO queueDTO = queueService.findQueueBy(request.getQueueName(), userDTO.getId());
        if (queueDTO == null) {
            throw new QueueNotFoundException();
        }
        messageRepository.acknowledgeMessage(queueDTO.getId(), request.getMessageIds());
    }

    private static ArrayList<Message> getMessageEntities(MessageRequest messageRequest, QueueDTO queueDTO) {
        var messages = new ArrayList<Message>();
        messageRequest.getMessages().forEach(message -> {
            Message messageToSend = new Message();
            messageToSend.setMessage(message);
            messageToSend.setQueueId(queueDTO.getId());
            messageToSend.setMessageId(UUID.randomUUID().toString());
            messages.add(messageToSend);
        });
        return messages;
    }

    private String getKey(String userName, String queueName) {
        return userName + ":" + queueName;
    }

    private MessageDTO getDto(Message message) {
        if (message == null) {
            return null;
        }
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(message, messageDTO);
        return messageDTO;
    }
}
