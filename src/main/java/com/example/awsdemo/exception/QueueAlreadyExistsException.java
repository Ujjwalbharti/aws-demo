package com.example.awsdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueueAlreadyExistsException extends CustomException {
    public QueueAlreadyExistsException() {
        super("Queue already exists", "ERR_106");
    }
}
