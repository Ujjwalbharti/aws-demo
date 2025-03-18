package com.example.awsdemo.exception;

public class QueueNotFoundException extends CustomException {
    public QueueNotFoundException() {
        super("Queue not found","ERR_107");
    }
}
