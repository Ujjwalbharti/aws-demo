package com.example.awsdemo.models.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePollRequest {

    @NotBlank(message = "Queue name cannot be empty")
    private String queueName;

    @Min(value = 1, message = "number of message to be poll, can not be zero")
    @Max(value = 50, message = "number of messages to be poll, can not be greater than 50")
    private int limit;
}
