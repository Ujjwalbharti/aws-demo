package com.example.awsdemo.service;

import com.example.awsdemo.entity.Queue;
import com.example.awsdemo.exception.CustomException;
import com.example.awsdemo.exception.QueueAlreadyExistsException;
import com.example.awsdemo.exception.QueueNotFoundException;
import com.example.awsdemo.models.QueueDTO;
import com.example.awsdemo.models.UserDTO;
import com.example.awsdemo.models.request.CreateQueueRequest;
import com.example.awsdemo.repository.QueueRepository;
import com.example.awsdemo.security.SecurityHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void createQueue(CreateQueueRequest createQueueRequest) throws CustomException {
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        UserDTO userDTO = userService.getUserByUsername(userName);
        Queue queue = queueRepository.findByQueueNameAndUserId(createQueueRequest.getQueueName(), userDTO.getId());
        if (queue != null) {
            throw new QueueAlreadyExistsException();
        }
        queue = new Queue();
        BeanUtils.copyProperties(createQueueRequest, queue);
        queue.setUserId(userDTO.getId());
        queueRepository.save(queue);
    }

    @Override
    @Transactional
    public void deleteQueue(String queueName) throws CustomException {
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        UserDTO userDTO = userService.getUserByUsername(userName);
        Queue queue = queueRepository.findByQueueNameAndUserId(queueName, userDTO.getId());
        if (queue == null) {
            throw new QueueNotFoundException();
        }
        queueRepository.delete(queue);
    }

    @Override
    public QueueDTO findQueueBy(String queueName, Long userId) throws CustomException {
        Queue queue = queueRepository.findByQueueNameAndUserId(queueName, userId);
        return getDto(queue);
    }

    @Override
    public QueueDTO findQueueBy(String queueName) throws CustomException {
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        UserDTO userDTO = userService.getUserByUsername(userName);
        Queue queue = queueRepository.findByQueueNameAndUserId(queueName, userDTO.getId());
        if (queue == null) {
            throw new QueueNotFoundException();
        }
        return getDto(queue);
    }

    @Override
    public List<QueueDTO> findAllQueues() throws CustomException {
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        UserDTO userDTO = userService.getUserByUsername(userName);
        List<Queue> queues = queueRepository.findByUserId(userDTO.getId());
        return queues.stream().map(this::getDto).toList();
    }

    private QueueDTO getDto(Queue queue) {
        if (queue == null) {
            return null;
        }
        QueueDTO queueDTO = new QueueDTO();
        BeanUtils.copyProperties(queue, queueDTO);
        return queueDTO;
    }
}
