package com.example.awsdemo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {
    private String message;
    private String messageId;
    private Long queueId;
}
