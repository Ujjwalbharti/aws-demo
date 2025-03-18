package com.example.awsdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PasswordNotMatchException extends CustomException {
    public PasswordNotMatchException() {
        super("Password not match", "ERR_105");
    }
}
