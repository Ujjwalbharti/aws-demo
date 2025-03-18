package com.example.awsdemo.repository;

import com.example.awsdemo.entity.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    @NativeQuery(value = """
            SELECT * FROM messages WHERE queue_id = :queueId
            AND (:messageIds IS NULL OR message_id NOT IN (:messageIds))
            ORDER BY created_at ASC LIMIT :limit
            """)
    List<Message> pollMessage(Long queueId, int limit, List<String> messageIds);

    @Modifying
    @NativeQuery(value = """
            Delete from messages where queue_id = :queueId AND message_id in (:messageIds)
            """)
    void acknowledgeMessage(Long queueId, List<String> messageIds);
}
