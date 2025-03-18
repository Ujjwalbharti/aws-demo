package com.example.awsdemo.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserNotAuthorisedException extends CustomException {
    public UserNotAuthorisedException() {
        super("User is not authorized", "ERR_103");
    }
}
