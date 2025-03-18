package com.example.awsdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailAlreadyExistsException extends CustomException {
    public EmailAlreadyExistsException() {
        super("Email already exists","ERR_101");
    }
}
