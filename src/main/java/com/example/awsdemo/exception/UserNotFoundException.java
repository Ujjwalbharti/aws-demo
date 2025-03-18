package com.example.awsdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super("User Not Found", "ERR_104");
    }
}
