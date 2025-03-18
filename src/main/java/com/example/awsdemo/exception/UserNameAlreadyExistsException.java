package com.example.awsdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserNameAlreadyExistsException extends CustomException {
    public UserNameAlreadyExistsException() {
        super("User name already exists", "ERR_102");
    }
}
