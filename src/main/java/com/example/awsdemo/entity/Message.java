package com.example.awsdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Table(name = "messages")
@Data
@Entity
public class Message extends AbstractEntity {

    @Column(name = "message")
    private String message;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "queue_id")
    private Long queueId;
}
