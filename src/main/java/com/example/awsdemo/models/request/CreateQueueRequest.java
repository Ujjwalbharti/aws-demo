package com.example.awsdemo.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQueueRequest {
    @NotBlank(message = "Queue name cannot be empty")
    private String queueName;

    @Min(value = 0, message = "Visibility timeout must be at least 0 seconds")
    @Max(value = 300, message = "Visibility timeout cannot be greater than 300 seconds")
    private int visibilityTimeout;
}
