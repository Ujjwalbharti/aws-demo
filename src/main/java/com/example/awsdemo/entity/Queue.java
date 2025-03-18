package com.example.awsdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Table(name = "queues")
@Data
@Entity
public class Queue extends AbstractEntity{

    @Column(name = "queue_name")
    private String queueName;

    @Column(name = "visibility_timeout")
    private Integer visibilityTimeout;

    @Column(name = "user_id")
    private Long userId;

}
