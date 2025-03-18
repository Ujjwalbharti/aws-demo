package com.example.awsdemo.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotBlank(message = "Queue name cannot be empty")
    private String queueName;

    @NotEmpty(message = "messages can not be empty")
    private List<String> messages;
}
