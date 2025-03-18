package com.example.awsdemo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueueDTO {
    private Long id;
    private String queueName;
    private Integer visibilityTimeout;
    private Long userId;
}
