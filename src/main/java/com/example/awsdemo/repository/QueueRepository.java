package com.example.awsdemo.repository;

import com.example.awsdemo.entity.Queue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends CrudRepository<Queue, Long> {
    Queue findByQueueNameAndUserId(String queueName, Long userId);

    List<Queue> findByUserId(Long userId);
}
